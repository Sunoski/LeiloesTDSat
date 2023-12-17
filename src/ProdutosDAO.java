/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try {
            Statement st;
            conn = new conectaDAO().connectDB(); // Conecta na DB
            st = conn.createStatement();
            String txt;
            txt = String.format("INSERT INTO `produtos` (`id`, `nome`, `valor`, `status`) VALUES (%s, '%s', %s, '%s');", produto.getId(), produto.getNome(), produto.getValor(), produto.getStatus());
            st.executeUpdate(txt);
        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando invalida ");
        }
    }
    public void venderProduto(int id) {
        try {
            Statement st;
            conn = new conectaDAO().connectDB(); // Conecta na DB
            st = conn.createStatement();
            String txt;
            txt = String.format("update produtos set Status='Vendido' where id=%s",id);
            st.executeUpdate(txt);
        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando invalida ");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            Statement st;
            conn = new conectaDAO().connectDB(); // Conecta na DB
            st = conn.createStatement();
            String txt;
            txt = String.format("select * from produtos");
            ResultSet rs = st.executeQuery(txt);
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(Integer.valueOf(rs.getString("valor")));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando invalida ");
        }
        return listagem;
    }
    
    
    
        
}

