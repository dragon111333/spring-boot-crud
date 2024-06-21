#### :)-> How to : https://spring.io/guides/gs/accessing-data-mysql 

## สำคัญ
### ใช้ SQL Server บน Window

1. สร้าง User/password สำหรับ CONNECT

2. เปิด TCP/IP สำหรับ SQL Server ด้วย
        
        Solution
        
        TCP/IP network traffic needs to be enabled on the SQL Server, so that remote connections will be allowed on the SQL server.
        
        1.Run the SQL Server Configuration Manager (mmc.exe).
        
        2.Click on the SQL Server Network Configuration.
        
        3.Select Protocols for SQLEXPRESS and check if the TCP/IP Protocol is enabled.
        
        4.If the TCP/IP is disabled, double-click on it and change the Enabled row status to Yes.
        
        5.In order to finish the adjustment, select SQL Server Services, click on SQL Server and restart it.
        
        6.SQL connection should now be available.


3. ต้อง Start service SQL SERVER BROWSER ด้วย!!!

### สร้าง SQL Server ด้วย Docker 
ขอบคุณ :` https://medium.com/mycostech/มาใช้-sql-server-บน-mac-ด้วย-docker-azure-data-studio-กันเถอะ-da9bf5c5a8d5`

1.)


      - สำหรับ Macbook chip Intel ใช้ Image ด้านล่างได้เลยครับ
         sudo docker pull mcr.microsoft.com/mssql/server:2022-latest
      - สำหรับ Macbook chip M1, M2 ใช้ Image นี้แทนครับ
           sudo docker pull mcr.microsoft.com/azure-sql-edge
      Note:
      **ทำไมถึงใช้ azure-sql-edge
      - จากวันนี้ที่ได้ลอง ถ้าเราใช้ image ของ mssql/server:2022-latest ใน chip M1 ตอนที่เรา start run container มันจะ Exit() ตลอดเลย
           ตัว Azure SQL Edge คืออะไร
      - คือตัว SQL Server/Azure SQL Database ที่ทำให้ขนาดมันเล็กลง กินทรัพยากรให้น้อยลง เพื่อที่จะสามารถเอาไปรันที่อุปกรณ์ปลายทางได้ (เช่น IoT) และทำงานได้แม้เราจะไม่ได้ต่อ internet (หลังจากนั้นค่อยส่งข้อมูลขึ้น cloud ภายหลังได้)


   2.)
      
      - สำหรับ Macbook chip Intel ใช้ command ด้านล่างได้เลยฮ่ะ (รหัสต้อง 10 ตัว)
        sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=<YourStrong@Passw0rd>" -p 1433:1433 --name sql_server -h sql_server -d mcr.microsoft.com/mssql/server:2022-latest
      - สำหรับ Macbook chip M1, M2 ใช้ command นี้แทนครับ (รหัสต้อง 10 ตัว)
        sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=<YourStrong@Passw0rd>" -p 1433:1433 --name sql_server -h sql_server -d mcr.microsoft.com/azure-sql-edge

   3.) เช็คใน docker desktop แล้วรัน container ได้เลย
### SQL
view
    
    DROP TABLE IF EXISTS [vw_user_exp];
    DROP VIEW IF EXISTS [vw_user_exp];
    
    CREATE VIEW [vw_user_exp] AS
    SELECT
        u.id AS user_id,
        u.name AS user_name,
        u.lastname AS user_lastname,
        e.id AS exp_id,
        e.name AS exp_name,
        e.start_at AS exp_start_at,
        e.end_at AS exp_end_at
    FROM
        (SELECT * FROM [user]) as u
    INNER JOIN
        (SELECT * FROM exp_info) as e
    ON u.id = e.user_id;

function
    
    DROP FUNCTION IF EXISTS get_user_exp;
    
    CREATE FUNCTION get_user_exp (@user_id INT)
    RETURNS NVARCHAR(255)
    AS
    BEGIN
    DECLARE @exp_year INT;
    DECLARE @exp_month INT;
    DECLARE @total_months INT;
    
        SELECT 
            @exp_year = SUM(DATEDIFF(YEAR, exp_start_at, exp_end_at)),
            @total_months = SUM(DATEDIFF(MONTH, exp_start_at, exp_end_at))
        FROM vw_user_exp
        WHERE user_id = @user_id
        GROUP BY user_id;
    
        -- Calculate remaining months after years have been accounted for
        SET @exp_month = @total_months % 12;
    
        RETURN CONCAT('{"exp_year":', @exp_year, ',"exp_month": ', @exp_month, '}');
    END;
    
    SELECT dbo.get_user_exp(1) AS user_experience;

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

* ### JWT
   ขอบคุณ : [https://vipawadeearmatsombut.medium.com/ตัวอย่างการใช้-jwt-ร่วมกับ-java-777c024b4279](https://vipawadeearmatsombut.medium.com/ตัวอย่างการใช้-jwt-ร่วมกับ-java-777c024b4279)