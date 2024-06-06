#### :)-> How to : https://spring.io/guides/gs/accessing-data-mysql 

### SQL
view

    CREATE VIEW vw_user_exp AS
    SELECT
        u.id AS user_id,
        u.name AS user_name,
        u.lastname AS user_lastname,
        e.id AS exp_id,
        e.name AS exp_name,
        e.start_at AS exp_start_at,
        e.end_at AS exp_end_at
    FROM
    (SELECT * FROM user) as u
    INNER JOIN
    (SELECT * FROM exp_info) as e
    ON u.id = e.user_id;
function

    DROP FUNCTION IF EXISTS get_user_exp;

    DELIMITER //
        
    CREATE FUNCTION get_user_exp(user_id INT) RETURNS VARCHAR(255) DETERMINISTIC
    CREATE VIEW vw_user_exp AS
    SELECT
        u.id AS user_id,
        u.name AS user_name,
        u.lastname AS user_lastname,
        e.id AS exp_id,
        e.name AS exp_name,
        e.start_at AS exp_start_at,
        e.end_at AS exp_end_at
    FROM
    (SELECT * FROM user) as u
    INNER JOIN
    (SELECT * FROM exp_info) as e
    ON u.id = e.user_id;
	BEGIN
		DECLARE exp_year INT(4);
		DECLARE exp_month INT(4);

		SELECT 
			 SUM(FLOOR(DATEDIFF(exp_end_at,exp_start_at)/365))
			,SUM(FLOOR(TIMESTAMPDIFF(MONTH,exp_start_at,exp_end_at)))
 		INTO exp_year,exp_month
		FROM vw_user_exp
		WHERE user_id =1
		GROUP BY user_id;

		RETURN CONCAT('{"exp_year":',exp_year,',"exp_month": ',exp_month,'}');
	END
	// 
	DELIMITER;


    SELECT get_user_exp(1) as user_exp_detail;

# **NOTE**

* ### **ถ้าไม่มี @JsonManagedReference , @JsonBackReference จะทำใหัเกิด Infinity return**

_ตัวอย่าง_
```@Entity
public class User {
// other fields

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Skill> skills;

    // getters and setters
}

@Entity
public class Skill {
// other fields

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // getters and setters
}
```
* ### Native Query

ใน Spring Data JPA, attribute nativeQuery ใน annotation `@Query` ใช้เพื่อระบุว่าคำสั่ง query ที่กำหนดนั้นเป็นคำสั่ง SQL ดั้งเดิม (native SQL) แทนที่จะเป็นคำสั่ง JPQL (Java Persistence Query Language) เมื่อ nativeQuery ถูกตั้งค่าเป็น true คำสั่ง query จะถูกดำเนินการโดยตรงกับฐานข้อมูล ทำให้คุณสามารถใช้ syntax และฟีเจอร์เฉพาะของ SQL ที่ฐานข้อมูลรองรับได้

_ตัวอย่าง JPQL_

@Repository

    public interface ViewUserExpInfoRepo extends CrudRepository<ViewUserExpInfo,Integer> {

        @Query(value="SELECT u FROM ViewUserExpInfo  u WHERE u.userId = :userId")
        List<ViewUserExpInfo> findByUserId(@Param("userId") Integer userId);

        @Query(value="SELECT u.* FROM vw_user_exp as u WHERE u.user_id = :userId",nativeQuery = true)
        List<ViewUserExpInfo> findByUserIdNative(@Param("userId") Integer userId);
    }

_ตัวอย่าง Native Query ของ MySQL_
