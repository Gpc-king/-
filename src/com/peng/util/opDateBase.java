package com.peng.util;

import com.peng.C3P0.C3P0Util;
import com.peng.bean.StuBean;
import com.peng.bean.StuBindID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Peng
 * @Date 2022/3/29 18:02
 * @Version 1.0
 */

public class opDateBase {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * 用于系统的登录
     *
     * @param qqNum：账号
     * @param password：密码
     * @return StuBindID:返回用户id，姓名，账号，用户类别
     */
    public StuBindID LoginSelect(String qqNum, String password) {

        StuBindID stuBindID = new StuBindID();
        String sql = "SELECT id,name,qqNum,types FROM stuInfo WHERE qqNum = ? AND password = ?";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, qqNum);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            stuBindID.setId(rs.getInt(1));
            stuBindID.setName(rs.getString(2));
            stuBindID.setQqnum(rs.getString(3));
            stuBindID.setType(rs.getInt(4));
            return stuBindID;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return stuBindID;
        } finally {
            new C3P0Util().setClose(conn, ps, rs);
        }
    }

    /**
     * 查询所有信息
     *
     * @return List<StuBean> : 用于存储学生信息
     */
    public List<StuBean> searchAllUser(int x, int y) {

        List<StuBean> stuList = new ArrayList<>();
        String sql = "SELECT * FROM stuInfo LIMIT ?, ?";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, x);
            ps.setInt(2, y);
            rs = ps.executeQuery();
            while (rs.next()) {
                StuBean stuBean = new StuBean();
                stuBean.setId(rs.getInt(1));
                stuBean.setQqNum(rs.getString(2));
                stuBean.setName(rs.getString(3));
                stuBean.setPassword(rs.getString(4));
                stuBean.setSex(rs.getString(5));
                stuBean.setBirth(rs.getDate(6));
                stuBean.setPhone(rs.getString(7));
                stuBean.setType(rs.getInt(8));
                stuList.add(stuBean);
            }
            return stuList;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return stuList;
        } finally {
            new C3P0Util().setClose(conn, ps, rs);
        }
    }

    /**
     * 查询所有信息
     *
     * @return List<StuBean> : 用于存储学生信息
     */
    public List<StuBean> searchAllUser(String name, int x, int y) {

        List<StuBean> stuList = new ArrayList<>();
        String sql = "SELECT * FROM stuInfo WHERE name LIKE ? LIMIT ?, ?";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, x);
            ps.setInt(3, y);
            rs = ps.executeQuery();
            while (rs.next()) {
                StuBean stuBean = new StuBean();
                stuBean.setId(rs.getInt(1));
                stuBean.setQqNum(rs.getString(2));
                stuBean.setName(rs.getString(3));
                stuBean.setPassword(rs.getString(4));
                stuBean.setSex(rs.getString(5));
                stuBean.setBirth(rs.getDate(6));
                stuBean.setPhone(rs.getString(7));
                stuBean.setType(rs.getInt(8));
                stuList.add(stuBean);
            }
            return stuList;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return stuList;
        } finally {
            new C3P0Util().setClose(conn, ps, rs);
        }
    }

    /**
     * @return 数据总数
     */
    public int getCountLine() {

        String sql = "SELECT COUNT(*) FROM stuInfo";
        int countLine = 0;
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                countLine = rs.getInt(1);
            }
            return countLine;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return countLine;
        } finally {
            new C3P0Util().setClose(conn, ps, rs);
        }
    }

    /**
     * @return 数据总数
     */
    public int getCountLine(String name) {

        String sql = "SELECT COUNT(*) FROM stuInfo WHERE name LIKE ?";
        int countLine = 0;
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                countLine = rs.getInt(1);
            }
            return countLine;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return countLine;
        } finally {
            new C3P0Util().setClose(conn, ps, rs);
        }
    }

    /**
     * 通过id查询用户的个人信息
     *
     * @param id
     * @return 个人信息
     */
    public StuBean searchInfoByID(int id) {

        StuBean stuBean = new StuBean();
        String sql = "SELECT * FROM stuInfo WHERE id = ?";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                stuBean.setId(rs.getInt(1));
                stuBean.setQqNum(rs.getString(2));
                stuBean.setName(rs.getString(3));
                stuBean.setPassword(rs.getString(4));
                stuBean.setSex(rs.getString(5));
                stuBean.setBirth(rs.getDate(6));
                stuBean.setPhone(rs.getString(7));
                stuBean.setType(rs.getInt(8));
            }
            return stuBean;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return stuBean;
        } finally {
            new C3P0Util().setClose(conn, ps, rs);
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return influenceLine：影响行数
     */
    public int deleteUser(int id) {
        int influenceLine = 0;

        String sql = "DELETE FROM stuInfo WHERE id = ?";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            influenceLine = ps.executeUpdate();
            return influenceLine;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return influenceLine;
        } finally {
            new C3P0Util().setClose(conn, ps, null);
        }
    }

    /**
     * 通过qqNum查询用户的个人信息
     *
     * @param qq
     * @return boolean
     */
    public boolean searchInfoByQQ(String qq) {

        StuBean stuBean = new StuBean();
        boolean isExist = false;
        String sql = "SELECT COUNT(*) FROM stuInfo WHERE qqNum = ?";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, qq);
            rs = ps.executeQuery();
            while (rs.next()) {
                isExist = rs.getInt(1) > 0 ? true : false;
            }
            return isExist;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return isExist;
        } finally {
            new C3P0Util().setClose(conn, ps, rs);
        }
    }

    /**
     * 插入用户
     *
     * @param stu
     * @return
     */
    public int insertUser(StuBean stu) {
        if (searchInfoByQQ(stu.getQqNum())) {
            return -1;
        }
        int influenceLine = 0;
        String sql = "INSERT INTO stuinfo(qqNum, name, password, sex, birth, phone, types) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getQqNum());
            ps.setString(2, stu.getName());
            ps.setString(3, stu.getPassword());
            ps.setString(4, stu.getSex());
            ps.setDate(5, new Date(stu.getBirth().getTime()));
            ps.setString(6, stu.getPhone());
            ps.setInt(7, stu.getTypes());
            System.out.println(stu.toString());
            influenceLine = ps.executeUpdate();
            return influenceLine;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return influenceLine;
        } finally {
            new C3P0Util().setClose(conn, ps, null);
        }
    }

    /**
     * 修改信息
     * @param stuBean
     * @return 返回影响行数
     */
    public int updateByID(StuBean stuBean) {
        int influenceLine = 0;
        if ("管理员".equals(stuBean.getType())){
            if (!searchInfoByQQ(stuBean.getQqNum())) {
                return -1;
            }
        }
        String sql =  "UPDATE stuinfo SET qqNum = ?, name = ?, password = ?, sex = ?, birth = ?, phone = ? WHERE id = ?";
        try {
            conn = new C3P0Util().getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stuBean.getQqNum());
            ps.setString(2, stuBean.getName());
            ps.setString(3, stuBean.getPassword());
            ps.setString(4, stuBean.getSex());
            ps.setDate(5, new Date(stuBean.getBirth().getTime()));
            ps.setString(6, stuBean.getPhone());
            ps.setInt(7, stuBean.getId());
            influenceLine = ps.executeUpdate();
            return influenceLine;
        } catch (SQLException e) {
            System.err.println(getClass() + " ----- " + e.getMessage());
            return influenceLine;
        } finally {
            new C3P0Util().setClose(conn, ps, null);
        }
    }

}
