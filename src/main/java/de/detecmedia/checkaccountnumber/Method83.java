package de.detecmedia.checkaccountnumber;

import de.detecmedia.checkaccountnumber.constants.AccountNumberPlace;
import de.detecmedia.checkaccountnumber.constants.Factor;
import de.detecmedia.checkaccountnumber.converter.Weighting;
import de.detecmedia.checkaccountnumber.exception.AccountNumberLenghtException;
import de.detecmedia.checkaccountnumber.exception.InvalidAcountNumberException;

import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * Kennzeichen 83. <p> 1. Kundenkonten</p> <p> A. Modulus 11, Gewichtung 2, 3,
 * 4, 5, 6, 7<br> B. Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br> C. Modulus 7,
 * Gewichtung 2, 3, 4, 5, 6</p> <p> Gemeinsame Anmerkungen für die
 * Berechnungsverfahren</p> <p> Die Kontonummer ist immer 10-stellig. Die für
 * die Berechnung relevante Kundennummer (K) befindet sich bei der Methode A in
 * den Stellen4 bis 9 der Kontonummer und bei den Methoden B + C in den Stellen
 * 5 - 9, die Prüfziffer in Stelle 10 der Kontonummer.</p> <p> Ergibt die erste
 * Berechnung der Prüfziffer nach dem Verfahren A einen Prüfzifferfehler, so
 * sind weitere Berechnungen mit den anderen Methoden vorzunehmen. Kontonummern,
 * die nach Durchführung aller 3 Berechnungsmethoden nicht zu einem richtigen
 * Ergebnis führen, sind nicht prüfbar.</p> <p> <b>Methode A:</b></p> <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p> <p> Die Berechnung und möglichen
 * Ergebnisse entsprechen dem Verfahren 32.</p>
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)
 * Kontonr.:   x x x K K K K K K P
 * Gewichtung:       7 6 5 4 3 2
 * </pre>
 * <p> Testkontonummern: 0001156071, 0001156136</p> <p> <b>Methode B:</b></p>
 * <p> Modulus 11, Gewichtung 2, 3, 4, 5, 6</p> <p> Die Berechnung und
 * möglichen Ergebnisse entsprechen dem Verfahren 33.</p>
 * <pre>Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10) Kontonr.: xxxxKKKKKP Gewichtung:
 * 6 5 4 3 2</pre>
 * <p> Testkontonummer: 0000156078</p> <p> <b>Methode C:</b></p> <p>
 * Kontonummern, die bis zur Methode C gelangen und in der 10. Stelle eine 7, 8
 * oder 9 haben, sind ungültig.</p> <p> Modulus 7, Gewichtung 2, 3, 4, 5, 6</p>
 * <p> Das Berechnungsverfahren entspricht Methode B.</p> <p> Die Summe der
 * Produkte ist jedoch durch 7 zu dividieren. Der verbleibende Rest wird vom
 * Divisor (7) subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt kein
 * Rest, ist die Prüfziffer 0.</p> <p> Testkontonummer: 0000156071</p> <p> <b>
 * 2. Sachkonten</b></p> <p> Berechnungsmethode: </p> <p> A. Modulus 11,
 * Gewichtung 2, 3, 4, 5, 6, 7, 8</p> <p> Die Sachkontonummer ist immer
 * 10-stellig.</p> <p> Die für die Berechnung relevante Sachkontenstammnummer
 * (S) befindet sich in den Stellen 3 bis 9 der Kontonummer, wobei die 3. und 4.
 * Stelle immer jeweils 9 sein müssen; die Prüfziffer ist in Stelle 10 der
 * Sachkontonummer.</p> <p> Führt die Berechnung nicht zu einem richtigen
 * Ergebnis, ist die Nummer nicht prüfbar.</p> <p> Methode A:</p> <p> Modulus
 * 11, Gewichtung 2, 3, 4, 5, 6, 7, 8</p> <p> Berechnung:</p> <p> Die einzelnen
 * Stellen der Sachkontonummern sind von rechts nach links mit den Ziffern 2, 3,
 * 4, 5, 6, 7, 8 zu multiplizieren. Die jeweiligen Produkte werden addiert.</p>
 * <p> Die Summe ist durch 11 zu dividieren. Der verbleibende Rest wird vom
 * Divisor (11) subtrahiert. Das Ergebnis ist die Prüfziffer.</p> <p> Verbleibt
 * nach der Division durch die 11 kein Rest, ist die Prüfziffer "0".</p> <p>
 * Das Rechenergebnis "10" ist nicht verwendbar und muss auf eine Stelle
 * reduziert werden. Die rechte Stelle Null findet als Prüfziffer
 * Verwendung.</p>
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)
 * Kontonr.:   x x S S S S S S S P
 * Gewichtung:     8 7 6 5 4 3 2
 * </pre>
 * <p> Testkontonummer: 0099100002</p> <p> Methode B:</p> <p> Modulus 11,
 * Gewichtung 2, 3, 4, 5, 6</p> <p> Die Berechnung und mögliche Ergebnisse
 * entsprechen dem Verfahren 33.</p>
 * <pre>Stellennr.: 1 2 3 4 5 6 7 8 9 A(A=10) Kontonr.: xxxxKKKKKP Gewichtung: 6
 * 5 4 3 2</pre>
 * <p> Testkontonummer: 0000156078</p> <p> Ergibt auch die Berechnung der
 * Prüfziffer nach Methode B einen Prüfzifferfehler, ist eine weitere
 * Berechnung mit der Methode C vorzunehmen.</p> <p> Methode C:</p> <p>
 * Kontonummern, die bis zur Methode C gelangen und in der 10. Stelle eine 7, 8
 * oder 9 haben, sind ungültig.</p> <p> Modulus 7, Gewichtung 2, 3, 4, 5, 6</p>
 * <p> Das Berechnungsverfahren entspricht Methode B.</p> <p> Die Summe der
 * Produkte ist jedoch durch 7 zu dividieren. Der verbleibende Rest wird vom
 * Divisor (7) subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt kein
 * Rest, ist die Prüfziffer 0.</p> <p> Testkontonummer: 0000156071</p> <p>
 * Ausnahme:</p> <p> Sind die 3. und 4. Stelle der Kontonummer = 99, so ist
 * folgende Prüfzifferberechnung maßgebend:</p> <p> Modulus: 11, Gewichtung 2,
 * 3, 4, 5, 6, 7, 8</p> <p> Die für die Berechnung relevanten Stellen 3 bis 9
 * der Kontonummer werden von rechts nach links mit den Ziffern 2, 3, 4, 5, 6,
 * 7, 8 multipliziert. Die weitere Berechnung und möglichen Ergebnisse
 * entsprechen dem Verfahren 02.</p> <p> Testkontonummer: 3199100002</p>
 *
 * @author Markus Potthast
 * @version 0.0.2
 */
public class Method83 extends AbstractMethod {

    /**
     * define logger.
     */
    private static final Logger LOG = Logger.getLogger(Method83.class);

    /**
     * Test method 83.
     *
     * @return boolean
     */
    @Override
    public boolean test() {
        int[] number = this.getAccountNumberArray();
        if (methodA(number)) {
            return true;
        }
        number = this.getAccountNumberArray();
        if (methodB(number)) {
            return true;
        }
        number = this.getAccountNumberArray();
        return methodC(number);
    }

    /**
     * <p>
     * <b>Methode A:</b></p>
     * <p>
     * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
     * <p>
     * Die Berechnung und möglichen Ergebnisse entsprechen dem Verfahren
     * 32.</p>
     * <pre>
     * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)
     * Kontonr.:   x x x K K K K K K P
     * Gewichtung:       7 6 5 4 3 2
     * </pre>
     * <p>
     * Testkontonummern: 0001156071, 0001156136</p>
     * <p>
     * <p>
     * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
     * <p>
     * Die Berechnung und möglichen Ergebnisse entsprechen dem Verfahren
     * 32.</p>
     * <pre>
     * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)
     * Kontonr.:   x x x K K K K K K P
     * Gewichtung:       7 6 5 4 3 2
     * </pre>
     * <p>
     * Testkontonummern: 0001156071, 0001156136</p>
     * <p>
     *
     * @param number account number array
     * @return boolean
     * @see Method32
     */
    final boolean methodA(final int[] number) {
        Method32 method32 = new Method32();
        method32.setAccountNumberArray(number.clone());
        return method32.check(Weighting.W_2_3_4_5_6_7)
                || methodASachkonten(number.clone());
    }

    /**
     * Factory sachkonten.
     *
     * @param number account number array
     * @return boolean
     */
    private boolean methodASachkonten(final int[] number) {
        int[] n = factor(number,
                Weighting.W_2_3_4_5_6_7_8,
                Factor.START_3RD,
                Factor.END_9TH
        );
        int pz = add(n, ADD_START_3RD, ADD_END_10TH);
        pz = modulus11(pz);
        return checkPz(pz, n);
    }

    /**
     * <b>Methode B .</b>
     * <p> Modulus 11, Gewichtung 2, 3, 4, 5, 6</p> <p>
     * Die Berechnung und möglichen Ergebnisse entsprechen dem Verfahren
     * 33.</p>
     * <pre>
     * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)
     * Kontonr.:   x x x x K K K K K P
     * Gewichtung:       6 5 4 3 2</pre>
     * <p> Testkontonummer: 0000156078</p> <p> Modulus 11, Gewichtung 2, 3, 4,
     * 5, 6</p> <p> Die Berechnung und mögliche Ergebnisse entsprechen dem
     * Verfahren 33.</p>
     * <pre>Stellennr.: 1 2 3 4 5 6 7 8 9 A(A=10) Kontonr.: xxxxKKKKKP
     * Gewichtung: 6 5 4 3 2</pre>
     * <p> Testkontonummer: 0000156078</p> <p> Ergibt auch die Berechnung der
     * Prüfziffer nach Methode B einen Prüfzifferfehler, ist eine weitere
     * Berechnung mit der Methode C vorzunehmen.</p>
     *
     * @param number account number array
     * @return boolean
     * @see Method33
     */
    final boolean methodB(final int[] number) {
        Method33 method33 = new Method33();
        method33.setAccountNumberArray(number);
        return method33.check(Weighting.W_2_3_4_5_6);
    }

    /**
     * <b>Methode C. </b>
     * <p>
     * Kontonummern, die bis zur Methode C gelangen und in der 10. Stelle eine
     * 7, 8 oder 9 haben, sind ungültig.</p>
     * <p>
     * Modulus 7, Gewichtung 2, 3, 4, 5, 6</p>
     * <p>
     * Das Berechnungsverfahren entspricht Methode B.</p>
     * <p>
     * Die Summe der Produkte ist jedoch durch 7 zu dividieren. Der verbleibende
     * Rest wird vom Divisor (7) subtrahiert. Das Ergebnis ist die Prüfziffer.
     * Verbleibt kein Rest, ist die Prüfziffer 0.</p>
     * <p>
     * Testkontonummer: 0000156071</p>
     * <p>
     * <b> 2. Sachkonten</b></p>
     * <p>
     * <p>
     * Kontonummern, die bis zur Methode C gelangen und in der 10. Stelle eine
     * 7, 8 oder 9 haben, sind ungültig.</p>
     * <p>
     * Modulus 7, Gewichtung 2, 3, 4, 5, 6</p>
     * <p>
     * Das Berechnungsverfahren entspricht Methode B.</p>
     * <p>
     * Die Summe der Produkte ist jedoch durch 7 zu dividieren. Der verbleibende
     * Rest wird vom Divisor (7) subtrahiert. Das Ergebnis ist die Prüfziffer.
     * Verbleibt kein Rest, ist die Prüfziffer 0.</p>
     * <p>
     * Testkontonummer: 0000156071</p>
     * <p>
     * Ausnahme:</p>
     * <p>
     * Sind die 3. und 4. Stelle der Kontonummer = 99, so ist folgende
     * Prüfzifferberechnung maßgebend:</p>
     * <p>
     * Modulus: 11, Gewichtung 2, 3, 4, 5, 6, 7, 8</p>
     * <p>
     * Die für die Berechnung relevanten Stellen 3 bis 9 der Kontonummer werden
     * von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 8 multipliziert.
     * Die weitere Berechnung und möglichen Ergebnisse entsprechen dem
     * Verfahren 02.</p>
     * <p>
     * Testkontonummer: 3199100002</p>
     *
     * @param number account number array
     * @return boolean
     * @see Method02
     */
    final boolean methodC(final int[] number) {
        int[] n = number.clone();
        if (n[AccountNumberPlace.P_10_TH] == ACCOUNT_NUMBER_IS_7
                || n[AccountNumberPlace.P_10_TH] == ACCOUNT_NUMBER_IS_8) {
            throw new InvalidAcountNumberException();
        }
        Method33 method33 = new Method33();
        if (n.length != ACCOUNT_NUMBER_LEGHT_10) {
            throw new AccountNumberLenghtException();
        }
        n = method33.factor(n,
                Weighting.W_2_3_4_5_6,
                Factor.START_5TH,
                Factor.END_9TH
        );
        int pz = method33.add(n, ADD_START_5TH, ADD_END_10TH);
        pz = modulus7(pz);
        if (method33.checkPz(pz, n)) {
            return true;
        }
        // reset number
        n = number;
        if (n[2] == ACCOUNT_NUMBER_IS_9
                && n[AccountNumberPlace.P_4_TH] == ACCOUNT_NUMBER_IS_9) {
            LOG.debug("ausname");
            LOG.debug("number : " + Arrays.toString(n));
            Method02 method02 = new Method02();
            method02.setAccountNumberArray(n);
            n = method02.factor(n,
                    Weighting.W_2_3_4_5_6_7_8,
                    Factor.START_3RD,
                    Factor.END_9TH
            );
            LOG.debug("number : " + Arrays.toString(n));
            pz = method02.add(n, ADD_START_3RD, ADD_END_10TH);
            pz = method02.modulus11(pz);
            return checkPz(pz, n);
        }
        return false;
    }

    /**
     * override modulus 11.
     *
     * @param number int
     * @return boolean
     */
    @Override
    protected int modulus11(final int number) {
        int checkDigit;
        int n = number % MODULUS_11;
        LOG.debug("%11: " + n);
        if (n == 0) {
            LOG.debug("pz 0: " + n);
            return 0;
        }
        if (n == RESULT_IS_10) {
            LOG.debug("InvalidException");
            throw new InvalidAcountNumberException();
        }
        checkDigit = MINUEND_CHECK_DIGIT - n;
        LOG.debug("pz all: " + checkDigit);
        return checkDigit;
    }

    /**
     * addition of accout number.
     *
     * @param number account number array
     * @param start  start pos
     * @param end    end pos
     * @return boolean
     */
    @Override
    protected int add(
            final int[] number, final int start, final int end
    ) {
        LOG.debug(start + " to " + end);
        int pz = 0;
        for (int i = start; i < end; i++) {
            int o = number[i];
            LOG.debug("+" + o);
            pz += o;
        }
        LOG.debug("after add: " + pz);
        return pz;
    }
}
