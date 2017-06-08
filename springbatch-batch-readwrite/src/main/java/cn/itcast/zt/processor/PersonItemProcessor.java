package cn.itcast.zt.processor;

import cn.itcast.zt.bean.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtian on 2017/6/8.
 */
@Service
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        person.setAge(person.getAge() + 1);
        person.setName(person.getName() + "-_-");
        return person;
    }
}
