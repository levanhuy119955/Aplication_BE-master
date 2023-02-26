package huylv.com.web.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Bạn phải nhập tên người dùng")
    private String userName;


    @Length(min = 8, max = 32, message = "Mật khẩu có độ dài 8 - 32 ký tự")
    @NotEmpty(message = "Bạn nhập mật khẩu")
    private String passWord;

    @Size(min = 10, max = 11)
    @NotNull(message = "Bạn nhập SĐT người dùng")
    private String number;

    @NotNull(message = "Bạn nhập email người dùng")
    @Column(unique = true)
    @Email(message = "Email không hợp lệ")
    private String email;

    @Column(nullable = false)
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleList;
}

