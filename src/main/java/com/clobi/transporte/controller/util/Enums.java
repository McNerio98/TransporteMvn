/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.util;

/**
 *
 * @author Tinkpad PC
 */
public class Enums {

    public static final class ESTADO_ACTIVIDAD {
        public static final short EJECUCION = 1;
        public static final short FINALIZADA = 2;
    }

    public static final class TIPO_FINANZA {
        public static final short INGRESO = 1;
        public static final short EGRESO = 2;
    }

    public static final class PAGO {
        public static final short COMBUSTIBLE = 1;
        public static final short DESPACHO = 2;
        public static final short MOTORISTA = 3;
        public static final short AYUDANTE = 4;
        public static final short AGUA = 5;
    }
}
