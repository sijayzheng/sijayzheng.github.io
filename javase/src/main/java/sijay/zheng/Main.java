package sijay.zheng;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<UserDTO1> list1 = new ArrayList<>();
        ArrayList<UserDTO2> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(new UserDTO1("name" + i, "email" + i));
        }
        for (int i = 0; i < 6; i++) {
            list2.add(new UserDTO2("name" + i, "email" + i));
        }
        Map<String, UserDTO1> map1 = list1.stream().collect(Collectors.toMap(UserDTO1::getName, v -> v));
        Map<String, UserDTO2> map2 = list2.stream().collect(Collectors.toMap(UserDTO2::getName, v -> v));

        for (String key : map1.keySet()) {
            UserDTO1 userDTO1 = map1.get(key);
            UserDTO2 userDTO2 = map2.get(key);
            if (Objects.isNull(userDTO2)) {

            } else {

            }
        }

    }
}

class UserDTO1 {
    private String name;
    private String email;

    public UserDTO1() {
    }

    public UserDTO1(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDTO1 userDTO1 = (UserDTO1) o;
        return Objects.equals(name, userDTO1.name) &&
                Objects.equals(email, userDTO1.email);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}

class UserDTO2 {
    private String name;
    private String email;

    public UserDTO2() {
    }

    public UserDTO2(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDTO2 userDTO1 = (UserDTO2) o;
        return Objects.equals(name, userDTO1.name) &&
                Objects.equals(email, userDTO1.email);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
