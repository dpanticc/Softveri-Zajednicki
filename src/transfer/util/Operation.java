/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int ADD_ADMINISTRATOR = 1;
    public static final int DELETE_ADMINISTRATOR = 2;
    public static final int UPDATE_ADMINISTRATOR = 3;
    public static final int GET_ALL_ADMINISTRATOR = 4;

    public static final int GET_ALL_GRAD = 5;

    public static final int ADD_TIM = 6;
    public static final int DELETE_TIM = 7;
    public static final int UPDATE_TIM = 8;
    public static final int GET_ALL_TIM = 9;

    public static final int ADD_IGRAC = 10;
    public static final int DELETE_IGRAC = 11;
    public static final int GET_ALL_IGRAC = 12;

    public static final int ADD_MEC = 13;
    public static final int GET_ALL_MEC = 14;
    
    public static final int ADD_STATISTIKA_IGRACA = 15;
    public static final int GET_ALL_STATISTIKA_IGRACA = 16;

}
