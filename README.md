:)-> How to : https://spring.io/guides/gs/accessing-data-mysql 


**NOTE**

* **ถ้าไม่มี @JsonManagedReference , @JsonBackReference จะทำใหัเกิด Infinity return**

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

