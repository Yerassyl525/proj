import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private static final String SELECT_ALL = "SELECT * FROM person";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id = ?";
    private static final String DELETE = "DELETE FROM person WHERE id = ?";
    private static final String SEARCH_NAME = "SELECT * FROM person WHERE name ILIKE ?";
    private static final String SEARCH_AGE = "SELECT * FROM person WHERE age = ?";

    public void insert(Person p) throws SQLException {
        String sql = """
            INSERT INTO person
            (id, role, name, age, gender,
             membership_type, monthly_fee,
             specialty, years_experience)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            fill(ps, p);
            ps.executeUpdate();
        }
    }

    public List<Person> getAll() throws SQLException {
        return queryList(SELECT_ALL);
    }

    public Person getById(int id) throws SQLException {
        List<Person> list = queryList(SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void update(Person p) throws SQLException {
        String sql = """
            UPDATE person SET
            role=?, name=?, age=?, gender=?,
            membership_type=?, monthly_fee=?,
            specialty=?, years_experience=?
            WHERE id=?
            """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            fill(ps, p);
            ps.setInt(9, p.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Person> searchByName(String key) throws SQLException {
        return queryList(SEARCH_NAME, "%" + key + "%");
    }

    public List<Person> searchByAge(int age) throws SQLException {
        return queryList(SEARCH_AGE, age);
    }

    private List<Person> queryList(String sql, Object... params) throws SQLException {
        List<Person> list = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++)
                ps.setObject(i + 1, params[i]);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    private void fill(PreparedStatement ps, Person p) throws SQLException {
        ps.setInt(1, p.getId());
        ps.setString(2, p instanceof Member ? "MEMBER" : "TRAINER");
        ps.setString(3, p.getName());
        ps.setInt(4, p.getAge());
        ps.setString(5, p.getGender());

        if (p instanceof Member m) {
            ps.setString(6, m.getMembershipType());
            ps.setDouble(7, m.getMonthlyFee());
            ps.setNull(8, Types.VARCHAR);
            ps.setNull(9, Types.INTEGER);
        } else {
            Trainer t = (Trainer) p;
            ps.setNull(6, Types.VARCHAR);
            ps.setNull(7, Types.NUMERIC);
            ps.setString(8, t.getSpecialty());
            ps.setInt(9, t.getYearsOfExperience());
        }
    }

    private Person map(ResultSet rs) throws SQLException {
        try {
            return "MEMBER".equals(rs.getString("role"))
                    ? new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("membership_type"),
                    rs.getDouble("monthly_fee")
            )
                    : new Trainer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("specialty"),
                    rs.getInt("years_experience")
            );
        } catch (InvalidInputException e) {
            throw new SQLException(e);
        }
    }
}
