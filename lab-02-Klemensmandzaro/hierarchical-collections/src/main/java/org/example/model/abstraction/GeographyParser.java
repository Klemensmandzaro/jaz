package org.example.model.abstraction;

import org.example.model.Geography;

import java.util.Objects;

import static java.sql.Types.NULL;

public class GeographyParser implements IParse<Geography> {
    @Override
    public Geography parse(String input) {
        input = input.trim();
        String[] tokens = input.split(";");
        Geography geography = new Geography();
        geography.setId(Integer.parseInt(tokens[0]));
        geography.setName(tokens[2]);
        geography.setType(tokens[1]);
        geography.setCode(tokens[3]);
        if (!Objects.equals(tokens[4], "NULL"))
        {
            geography.setParentId(Integer.parseInt(tokens[4]));
        }

        return geography;
    }
}
