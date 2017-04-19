package cn.itcast.zt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class SpringootStatemachineApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringootStatemachineApplication.class, args);
	}

	@Autowired(required = false)
	private StateMachine<States, Events> stateMachine ;

	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}
}
