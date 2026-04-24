package iuh.fit;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.core.dto.DepartmentDTO;
import iuh.fit.core.entity.Department;

import java.util.Map;

public class JacksonDemo {

	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		// Map -> Entity
		Map<String, Object> map = Map.of(
				"id", "CS",
				"name", "Computer Science",
				"building", "V",
				"room", "V3.1",
				"dean", "Dr. Smith"
		);

		Department department =
				mapper.convertValue(map, Department.class);

		// Entity -> DTO
		DepartmentDTO dto =
				mapper.convertValue(department, DepartmentDTO.class);

		System.out.println("Entity:");
		System.out.println(department);

		System.out.println("DTO:");
		System.out.println(dto);
	}
}