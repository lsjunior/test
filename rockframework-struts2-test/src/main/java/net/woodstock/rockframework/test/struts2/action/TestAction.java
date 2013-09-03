package net.woodstock.rockframework.test.struts2.action;

import java.util.Map.Entry;

import net.woodstock.rockframework.test.struts2.entity.Foo;
import net.woodstock.rockframework.web.struts2.Action;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.Dispatcher;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.config.entities.PackageConfig;

@ParentPackage(value = "default")
@org.apache.struts2.convention.annotation.Action(value = "test")
@Results(value = { @Result(name = "success", location = "/test/test.jsp") })
@net.woodstock.rockframework.web.struts2.spring.Action
@Scope(value = "prototype")
public class TestAction extends Action {

	private static final long	serialVersionUID	= 1L;

	private Foo					foo;

	private Object				object;

	public TestAction() {
		super();
	}

	@Override
	public String execute() throws Exception {
		return net.woodstock.rockframework.web.struts2.Constants.SUCCESS;
	}

	public Foo getFoo() {
		return this.foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public Object getObject() {
		return this.object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		ConfigurationManager manager = Dispatcher.getInstance().getConfigurationManager();
		Configuration configuration = manager.getConfiguration();

		String extension = configuration.getContainer().getInstance(String.class, "struts.action.extension");

		for (Entry<String, PackageConfig> entryPackage : configuration.getPackageConfigs().entrySet()) {
			PackageConfig packageConfig = entryPackage.getValue();
			builder.append("Package: " + packageConfig.getName() + "(" + packageConfig.getNamespace() + ")\n");
			for (Entry<String, ActionConfig> entryConfig : packageConfig.getActionConfigs().entrySet()) {
				ActionConfig actionConfig = entryConfig.getValue();
				builder.append("\t" + actionConfig.getName() + "." + extension + "\n");
			}
		}
		String message = builder.toString();
		System.out.println(message);
		return message;
	}

}
