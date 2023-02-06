package co.yedam.emp.vo;

import lombok.Data;

//@Getter, @Setter, @@AllArgsConstructor(생성자), @@ToString, @@NoArgsConstructor 등 모두 들어있음
@Data 

public class EmpVO {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String jobId;
	private String hireDate;
}
