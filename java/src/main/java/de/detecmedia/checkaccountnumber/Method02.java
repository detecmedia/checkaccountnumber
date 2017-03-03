package de.detecmedia.checkaccountnumber;

import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 * Kennzeichen 02.
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 2
 * </p>
 * <p>
 * Die Stellen der Kontonummer sind von rechts nach links mit den Ziffern 2, 3,
 * 4, 5, 6, 7, 8, 9, 2 zu multiplizieren. Die jeweiligen Produkte werden
 * addiert. Die Summe ist durch 11 zu dividieren. Der verbleibende Rest wird vom
 * Divisor (11) subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt nach
 * der Division durch 11 kein Rest, ist die Prüfziffer 0. Ergibt sich als Rest
 * 1, ist die Prüfziffer zweistellig und kann nicht verwendet werden. Die
 * Kontonummer ist dann nicht verwendbar.
 * </p>
 *
 * @author Markus Potthast
 * @version 0.0.2
 */
public class Method02 extends AbstractMethod {

    private static Logger log = Logger.getLogger(Method02.class);

    @Override
    public boolean test() {
        weighting = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 2};
        return check(weighting);
    }

    protected boolean check(int[] weighting) {
        log.debug("factor: " + Arrays.toString(weighting));
        int[] number = this.getAccountNumberArray();
        log.debug("number befor method->factor: " + Arrays.toString(number));
        number = this.factor(number, weighting);
        log.debug("number after method->factor: " + Arrays.toString(number));
        int pz = add(number, 0, number.length - 1);

        pz = this.modulus11(pz);

        return this.checkPz(pz, number);
    }

    @Override
    protected int add(int[] number, int start, int end) {
        log.debug(start + " to " + end);
        int pz = 0;
        for (int i = start--; i < end; i++) {
            log.debug("+" + number[i]);
            int o = number[i];
            pz += o;
        }
        log.debug("after add: " + pz);
        return pz;
    }

}