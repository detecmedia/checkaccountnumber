/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.detecmedia.checkaccountnumber.exception;

import org.apache.log4j.Logger;

/**
 * @author Markus Potthast
 */
public class InvalidException extends RuntimeException {

    /**
     * Logger.
     */
    private Logger logger = Logger.getLogger(InvalidException.class);

    /**
     * Exception without defined error message.
     * @param message error message
     */
    public InvalidException(final String message) {
        super(message);
        logger.error(this.getMessage());
    }


}
