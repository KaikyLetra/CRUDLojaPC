package com.template;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import com.template.Conexao;

public class ComponentesDAO {
    Connection c;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<ComponentesDTO> listaComponentes = new ArrayList<>();

    public ArrayList<ComponentesDTO> selectComponentes() {
        rs = null;
        ps = null;
        c = new Conexao().conectaBD();

        try {
            ps = c.prepareStatement("select * from componentes");
            rs = ps.executeQuery();

            while (rs.next()) {
                ComponentesDTO componentes = new ComponentesDTO();
                componentes.setIdPc(rs.getInt("id"));
                componentes.setNome(rs.getString("nome"));
                componentes.setGabinete(rs.getString("gabinete"));
                componentes.setCpu(rs.getString("Cpu"));
                componentes.setGpu(rs.getString("Gpu"));
                componentes.setRam(rs.getString("Ram"));
                componentes.setDualchannel(rs.getBoolean("dChannel"));
                componentes.setArmazenamento(rs.getString("armaz"));
                componentes.setBluetooth(rs.getBoolean("bluetooth"));
                listaComponentes.add(componentes);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaComponentes;
    }

    public void insertComponente(ComponentesDTO componentes) {
        String sql = " INSERT INTO componentes (nome, gabinete, cpu, gpu, ram, dualchannel, armazenamento, bluetooth) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, componentes.getNome());
            ps.setString(2, componentes.getGabinete());
            ps.setString(3, componentes.getCpu());
            ps.setString(4, componentes.getGpu());
            ps.setString(5, componentes.getRam());
            ps.setBoolean(6, componentes.isDualchannel());
            ps.setString(7, componentes.getArmazenamento());
            ps.setBoolean(8, componentes.isBluetooth());

            ps.execute();
            System.out.println("Componente cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateComponente(ComponentesDTO componentes) {
        String sql = "UPDATE componentes SET nome=?, gabinete=?, cpu=?, gpu=?, ram=?, dualchannel=?, armazenamento=?, bluetooth=? "
                + " WHERE id_pc=?";

        try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, componentes.getNome());
            ps.setString(2, componentes.getGabinete());
            ps.setString(3, componentes.getCpu());
            ps.setString(4, componentes.getGpu());
            ps.setString(5, componentes.getRam());
            ps.setBoolean(6, componentes.isDualchannel());
            ps.setString(7, componentes.getArmazenamento());
            ps.setBoolean(8, componentes.isBluetooth());
            ps.setInt(9, componentes.getIdPc());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteComponente(ComponentesDTO componentes)

    {
        String sql = "DELETE FROM componentes WHERE id_pc=?";

        try(Connection c = new Conexao().conectaBD(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, componentes.getIdPc());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (SQLException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }
}
