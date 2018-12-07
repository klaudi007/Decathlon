package com.roadlog.util;

import com.roadlog.exception.DecathlonException;
import com.roadlog.model.Athletes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.roadlog.cons.App.CSV_SEPARATOR;

/**
 * @author MusaAl
 * @date 12/7/2018 : 4:50 PM
 */
public class Connector {

    public static String input;

    public static String output;

    private static final Validator validator = new ValidatorImpl();

    public List<String[]> readStream(){
        String line;
        String separator = CSV_SEPARATOR;
        List<String[]> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            while ((line = br.readLine()) != null) {
                results.add(line.split(separator));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void writeStrem(Athletes athletes){
        try{
            File file = new File(output);
            JAXBContext ctx = JAXBContext.newInstance(Athletes.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(athletes, file);
            marshaller.marshal(athletes, System.out);
        }catch(JAXBException e){
            e.printStackTrace();
        }
    }



}
