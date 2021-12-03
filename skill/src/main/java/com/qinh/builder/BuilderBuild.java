package com.qinh.builder;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * 构建者模式
 *
 * @author Qh
 * @version 1.0
 * @date 2021/12/3 16:43
 */
public class BuilderBuild {

    public static void main(String[] args){
        Person person = Person.builder().name("demo").age(12).hobby("fly").build();
    }
}

@Builder
class Person {
    private String name;

    private int age;

    private String hobby;
}

@Builder
class User {
    private final Integer id;
    private final String zipCode = "123456";
    private String username;
    private String password;
    @Singular
    private List<String> hobbies;
}

/**
 * // 编译后：
 * public class User {
 *     private final Integer id;
 *     private final String zipCode = "123456";
 *     private String username;
 *     private String password;
 *     private List<String> hobbies;
 *     User(Integer id, String username, String password, List<String> hobbies) {
 *         this.id = id; this.username = username;
 *         this.password = password; this.hobbies = hobbies;
 *     }
 *
 *     public static User.UserBuilder builder() {return new User.UserBuilder();}
 *
 *     public static class UserBuilder {
 *         private Integer id;
 *         private String username;
 *         private String password;
 *         private ArrayList<String> hobbies;
 *         UserBuilder() {}
 *         public User.UserBuilder id(Integer id) { this.id = id; return this; }
 *         public User.UserBuilder username(String username) { this.username = username; return this; }
 *         public User.UserBuilder password(String password) { this.password = password; return this; }
 *
 *         public User.UserBuilder hobby(String hobby) {
 *             if (this.hobbies == null) {
 *                 this.hobbies = new ArrayList();
 *             }
 *             this.hobbies.add(hobby);
 *             return this;
 *         }
 *
 *         public User.UserBuilder hobbies(Collection<? extends String> hobbies) {
 *             if (this.hobbies == null) {
 *                 this.hobbies = new ArrayList();
 *             }
 *             this.hobbies.addAll(hobbies);
 *             return this;
 *         }
 *
 *         public User.UserBuilder clearHobbies() {
 *             if (this.hobbies != null) {
 *                 this.hobbies.clear();
 *             }
 *             return this;
 *         }
 *
 *         public User build() {
 *             List hobbies;
 *             switch(this.hobbies == null ? 0 : this.hobbies.size()) {
 *             case 0:
 *                 hobbies = Collections.emptyList();
 *                 break;
 *             case 1:
 *                 hobbies = Collections.singletonList(this.hobbies.get(0));
 *                 break;
 *             default:
 *                 hobbies = Collections.unmodifiableList(new ArrayList(this.hobbies));
 *             }
 *             return new User(this.id, this.username, this.password, hobbies);
 *         }
 *         public String toString() {
 *             return "User.UserBuilder(id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", hobbies=" + this.hobbies + ")";
 *         }
 *     }
 * }
 */
