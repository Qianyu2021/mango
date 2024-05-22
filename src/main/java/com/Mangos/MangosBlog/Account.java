package com.Mangos.MangosBlog;

/*
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    private String email;
    @JsonIgnore  //this will not show the password
    private String password;
    private String firstName;
    private String lastName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<BlogPost> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="authority_name", referencedColumnName = "name")})
    Set<Authority> authorities = new HashSet<>();

    )
}
*/