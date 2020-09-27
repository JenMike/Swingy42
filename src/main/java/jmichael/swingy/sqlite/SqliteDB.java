package jmichael.swingy.sqlite;

import jmichael.swingy.beans.Armor;
import jmichael.swingy.beans.Helmet;
import jmichael.swingy.beans.Weapon;
import jmichael.swingy.beans.avatar.Hero;
import jmichael.swingy.beans.avatar.AvatarBean;

import java.sql.*;
import java.util.ArrayList;


public class SqliteDB {
    private static final String SQL_URL = "jdbc:sqlite::resource:swingy.db";
    private static Connection connection;

    public static void connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(SQL_URL);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        connection = conn;
    }

    public static void close() {
        try {
            if (connection != null)
                connection.close();
            connection = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Connection getConnection() {
        if (connection == null)
            connect();
        return connection;
    }

    public static int insert(String name, String className, int level, int xp, int attack, int defense, int hp) {
        String sqlQuery = "INSERT INTO heroes(name, class, level, xp, attack, defense, hp) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int id = 0;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)) {
            pstmt.setString(1, name);
            pstmt.setString(2, className);
            pstmt.setInt(3, level);
            pstmt.setInt(4, xp);
            pstmt.setInt(5, attack);
            pstmt.setInt(6, defense);
            pstmt.setInt(7, hp);
            pstmt.executeUpdate();

            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT seq FROM sqlite_sequence WHERE name=\"heroes\"");
            if (rs.next())
                id = rs.getInt("seq");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public static ArrayList<String> selectAll() {
        String sqlQuery = "SELECT * FROM heroes";
        ArrayList<String> arrayList = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            for (int i = 1; rs.next(); i++) {
                arrayList.add(String.format("%d. %s (%s)", i, rs.getString("name"), rs.getString("class")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    public static Hero selectHeroById(int id) {
        String sqlQuery = "SELECT * FROM heroes WHERE id = ?";
        Hero hero = null;

        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                AvatarBean builder = new AvatarBean();
                builder.setId(rs.getInt("id"));
                builder.setName(rs.getString("name"));
                builder.setHeroClass(rs.getString("class"));
                builder.setLevel(rs.getInt("level"));
                builder.setExperience(rs.getInt("xp"));
                builder.setAttack(rs.getInt("attack"));
                builder.setDefense(rs.getInt("defense"));
                builder.setHitPoints(rs.getInt("hp"));

                if (rs.getString("weapon_name") != null)
                    builder.setWeapon(new Weapon(rs.getString("weapon_name"), rs.getInt("weapon_value")));
                if (rs.getString("helm_name") != null)
                    builder.setHelm(new Helmet(rs.getString("helm_name"), rs.getInt("helm_value")));
                if (rs.getString("armor_name") != null)
                    builder.setArmor(new Armor(rs.getString("armor_name"), rs.getInt("armor_value")));

                hero = builder.getHero();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hero;
    }

    public static void updateAvatarStats(Hero hero) {
        String sqlQuery = "UPDATE heroes SET level = ?, xp = ?, attack = ?, defense = ?, hp = ? , " +
                "weapon_name = ?, weapon_value = ?, helm_name = ?, helm_value = ?, armor_name = ?, armor_value = ? " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)) {
            pstmt.setInt(1, hero.getLevel());
            pstmt.setInt(2, hero.getExperience());
            pstmt.setInt(3, hero.getAttack());
            pstmt.setInt(4, hero.getDefense());
            pstmt.setInt(5, hero.getHitPoints());

            if (hero.getWeapon() != null) {
                pstmt.setString(6, hero.getWeapon().getName());
                pstmt.setInt(7, hero.getWeapon().getPoints());
            }
            if (hero.getHelmet() != null) {
                pstmt.setString(8, hero.getHelmet().getName());
                pstmt.setInt(9, hero.getHelmet().getPoints());
            }
            if (hero.getArmor() != null) {
                pstmt.setString(10, hero.getArmor().getName());
                pstmt.setInt(11, hero.getArmor().getPoints());
            }

            pstmt.setInt(12, hero.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
