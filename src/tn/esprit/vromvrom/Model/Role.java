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
public class Role {
    
       private int id;
    private String role;

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", role=" + role + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
