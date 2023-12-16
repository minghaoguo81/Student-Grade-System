/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.db.StudentDB;
import com.example.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author guomi
 */
public class StudentDAOImp implements StudentDAO {

    @Override
    public void add(Student students) {
        
        try {
            Connection con = StudentDB.getConnection();
            String sql = "INSERT INTO students(firstName, lastName, emailAddress, grade) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, students.getFirstName());
            ps.setString(2, students.getLastName());
            ps.setString(3, students.getEmailAddress());
            ps.setString(4, students.getGrade());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Added!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public void update(Student students) {
        
        try {
            Connection con = StudentDB.getConnection();
            String sql = "UPDATE students SET firstName=?,lastName=?,emailAddress=?,grade=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, students.getFirstName());
            ps.setString(2, students.getLastName());
            ps.setString(3, students.getEmailAddress());
            ps.setString(4, students.getGrade());
            ps.setInt(5, students.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Updated!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public void delete(Student students) {
        
        try {
            Connection con = StudentDB.getConnection();
            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, students.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public Student get(int id) {
        
        Student st = new Student();
        try {
            Connection con = StudentDB.getConnection();
            String sql = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                st.setId(rs.getInt("id"));
                st.setFirstName(rs.getString("firstName"));
                st.setLastName(rs.getString("lastName"));
                st.setEmailAddress(rs.getString("emailAddress"));
                st.setGrade(rs.getString("grade"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return st;
    }

    @Override
    public List<Student> list() {
        
        List<Student> list = new ArrayList<Student>();
        try {
            Connection con = StudentDB.getConnection();
            String sql = "SELECT * FROM students ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setFirstName(rs.getString("firstName"));
                st.setLastName(rs.getString("lastName"));
                st.setEmailAddress(rs.getString("emailAddress"));
                st.setGrade(rs.getString("grade"));
                
                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return list;
    }
    
}
