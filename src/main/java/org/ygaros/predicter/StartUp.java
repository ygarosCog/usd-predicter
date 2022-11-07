package org.ygaros.predicter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.ygaros.predicter.data.NBPResponse;
import org.ygaros.predicter.domain.Domain;
import org.ygaros.predicter.domain.USDCaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
public class StartUp implements CommandLineRunner {

    private final USDCaller caller;
    private final Domain domain;

    @Autowired
    public StartUp(USDCaller caller,
                   Domain domain){
        this.caller = caller;
        this.domain = domain;
    }

    @Override
    public void run(String... args) throws Exception {
        String path = new ClassPathResource("application.yml").getPath();
        path = path.replace("application.yml", "data\\");

        File file = new File(path + "data.txt");

        List<LocalDate[]> dates = getDates();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Marshaller marshaller = JAXBContext.newInstance(NBPResponse.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for(LocalDate[] d : dates){
            //marshaller.marshal(this.caller.getRatesFor(d[0], d[1]), writer);
            //writer.println(mapper.writeValueAsString(this.caller.getRatesFor(d[0], d[1])));
        }
        //writer.flush();
        //writer.close();
        System.out.println(file.getAbsolutePath());
    }
    public List<LocalDate[]> getDates(){

        List<LocalDate[]> dates = new ArrayList<>();
        dates.add(new LocalDate[]{LocalDate.parse("2002-01-02"), LocalDate.parse("2002-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2002-06-03"), LocalDate.parse("2003-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2003-01-03"), LocalDate.parse("2003-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2003-06-03"), LocalDate.parse("2004-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2004-01-03"), LocalDate.parse("2004-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2004-06-03"), LocalDate.parse("2005-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2005-01-03"), LocalDate.parse("2005-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2005-06-03"), LocalDate.parse("2006-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2006-01-03"), LocalDate.parse("2006-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2006-06-03"), LocalDate.parse("2007-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2007-01-03"), LocalDate.parse("2007-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2007-06-03"), LocalDate.parse("2008-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2008-01-03"), LocalDate.parse("2008-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2008-06-03"), LocalDate.parse("2009-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2009-01-03"), LocalDate.parse("2009-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2009-06-03"), LocalDate.parse("2010-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2010-01-03"), LocalDate.parse("2010-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2010-06-03"), LocalDate.parse("2011-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2011-01-03"), LocalDate.parse("2011-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2011-06-03"), LocalDate.parse("2012-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2012-01-03"), LocalDate.parse("2012-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2012-06-03"), LocalDate.parse("2013-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2013-01-03"), LocalDate.parse("2013-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2013-06-03"), LocalDate.parse("2014-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2014-01-03"), LocalDate.parse("2014-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2014-06-03"), LocalDate.parse("2015-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2015-01-03"), LocalDate.parse("2015-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2015-06-03"), LocalDate.parse("2016-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2016-01-03"), LocalDate.parse("2016-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2016-06-03"), LocalDate.parse("2017-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2017-01-03"), LocalDate.parse("2017-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2017-06-03"), LocalDate.parse("2018-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2018-01-03"), LocalDate.parse("2018-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2018-06-03"), LocalDate.parse("2019-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2019-01-03"), LocalDate.parse("2019-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2019-06-03"), LocalDate.parse("2020-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2020-01-03"), LocalDate.parse("2020-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2020-06-03"), LocalDate.parse("2021-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2021-01-03"), LocalDate.parse("2021-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2021-06-03"), LocalDate.parse("2022-01-02")});

        dates.add(new LocalDate[]{LocalDate.parse("2022-01-03"), LocalDate.parse("2022-06-02")});
        dates.add(new LocalDate[]{LocalDate.parse("2022-06-03"), LocalDate.parse("2022-10-10")});
        return dates;
    }
}
