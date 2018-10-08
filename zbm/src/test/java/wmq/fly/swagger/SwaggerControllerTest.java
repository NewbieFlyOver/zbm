package wmq.fly.swagger;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import springfox.documentation.staticdocs.SwaggerResultHandler;

@WebAppConfiguration
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SwaggerControllerTest {

	private String snippetDir = "target/generated-snippets";    
	private String outputDir  = "target/asciidoc";  
	
	@Autowired    
	private MockMvc mockMvc;   
	
	@Test  
	public void Test() throws Exception {        
		// 得到swagger.json,写入outputDir目录中       
		mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))                
		.andDo(SwaggerResultHandler.outputDirectory(outputDir).build())                
		.andExpect(status().isOk())                
		.andReturn();         // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir       
		// 这个outputDir必须和插件里面<generated></generated>标签配置一致       
		Swagger2MarkupConverter.from(outputDir + "/swagger.json")                
		//.withPathsGroupedBy(GroupBy.TAGS)// 按tag排序               
		.withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式                
		.withExamples(snippetDir)               
		.build()               
		.intoFolder(outputDir);// 输出           
		}    
	@Test    
	public void TestApi() throws Exception {}
	}



