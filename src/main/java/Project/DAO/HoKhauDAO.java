package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.HoKhau;
import Project.Model.NhanKhau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoKhauDAO implements DAO<HoKhau> {

    @Override
    public int insert(HoKhau o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO ho_khau(ID,maHoKhau,idChuHo,maKhuVuc,diaChi,ngayLap)"
                    +" VALUES(?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setString(2,o.getMaHoKhau());
            st.setInt(3,o.getIdChuHo());
            st.setString(4,o.getMaKhuVuc());
            st.setString(5,o.getDiaChi());
            st.setDate(6,o.getNgayLap());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;
    }

    @Override
    public int update(HoKhau o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE ho_khau "+"SET maHoKhau = ?,idChuHo = ?, maKhuVuc = ?, diaChi = ?, ngayLap"
                    +"WHERE ID = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,o.getMaHoKhau());
            st.setInt(2,o.getIdChuHo());
            st.setString(3,o.getMaKhuVuc());
            st.setString(4,o.getDiaChi());
            st.setDate(5,o.getNgayLap());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public int delete(HoKhau o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from ho_khau "+"WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public ArrayList<HoKhau> selectAll() {
        ArrayList<HoKhau> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM ho_khau";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                HoKhau o=new HoKhau();
                o.setID(rs.getInt("ID"));
                o.setmaHoKhau(rs.getString("maHoKhau"));
                o.setDiaChi(rs.getString("diaChi"));
                o.setIdChuHo(rs.getInt("idChuHo"));
                o.setMaKhuVuc(rs.getString("maKhuVuc"));
                o.setNgayLap(rs.getDate("ngayLap"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
