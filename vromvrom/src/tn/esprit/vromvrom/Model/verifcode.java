/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class verifcode {
    private int id_code;
    private String code;
    private String  codeQR;

    public verifcode(int id_code, String code, String codeQR) {
        this.id_code = id_code;
        this.code = code;
        this.codeQR = codeQR;
    }

    public int getId_code() {
        return id_code;
    }

    public void setId_code(int id_code) {
        this.id_code = id_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeQR() {
        return codeQR;
    }

    public void setCodeQR(String codeQR) {
        this.codeQR = codeQR;
    }

    @Override
    public String toString() {
        return "verifcode{" + "id_code=" + id_code + ", code=" + code + ", codeQR=" + codeQR + '}';
    }
   

    

}
