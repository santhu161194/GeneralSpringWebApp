package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	@ResponseBody  
	public String showLogin(@RequestParam("value") final String value,HttpServletRequest request, HttpServletResponse response) {
		KeyHolder generatedKeyHolder=new GeneratedKeyHolder();
		try {
		
		jdbcTemplate.update(new PreparedStatementCreator() {
		    	
				
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						connection.setAutoCommit(false);
						String sql="INSERT INTO test.demo(value) VALUES	(?)";

						PreparedStatement ps = connection.prepareStatement(sql.toString(),
						    Statement.RETURN_GENERATED_KEYS); 
						ps.setInt(1, Integer.valueOf(value));
						return ps;
					
                   
				}
			}, generatedKeyHolder);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
	}
		
		
		
			//System.out.println("generatedKeyHolder: "+generatedKeyHolder.getKey().longValue());
		    return String.valueOf(generatedKeyHolder.getKey().longValue());

		  }
	
	
}
