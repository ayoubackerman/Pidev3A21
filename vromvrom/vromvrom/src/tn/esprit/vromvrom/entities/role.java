/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.entities;

import java.util.Objects;

public class role {
    private int id_role;
    private String role;

    public role() {
    }

    public role(String role) {
        this.role = role;
    }

    public role(int id_role, String role) {
        this.id_role = id_role;
        this.role = role;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        role role1 = (role) o;
        return id_role == role1.id_role && Objects.equals(role, role1.role);
    }

    @Override
    public String toString() {
        return "role{" +
                "id_role=" + id_role +
                ", role='" + role + '\'' +
                '}';
    }
}
