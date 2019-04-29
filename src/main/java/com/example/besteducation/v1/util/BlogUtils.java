package com.example.besteducation.v1.util;

import com.example.besteducation.v1.domain.Course;
import com.example.besteducation.v1.domain.MailMessage;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BlogUtils {

    public static ArrayList<Course> copyCourse(List<Course> old){
        ArrayList<Course> list=new ArrayList<>();
        for(Course c:old)
        {
            list.add(c);
        }
        return list;
    }

    public static ArrayList<MailMessage> copyMail(List<MailMessage> old){
        ArrayList<MailMessage> list=new ArrayList<>();
        for(MailMessage c:old)
        {
            list.add(c);
        }
        return list;
    }

    public static List<MailMessage> combine(List<MailMessage> mailMessages1,
                                            List<MailMessage> mailMessages2){

        HashSet<Long> set1=new HashSet<>();
        List<MailMessage> res=new ArrayList<>();
        for(MailMessage m:mailMessages1)
            set1.add(m.getId());
        for(MailMessage m:mailMessages2)
            if(set1.contains(m.getId()))
                res.add(m);
        return res;
    }
}
