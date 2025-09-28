package com.example.sitpassbek.indexModel;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "facility_index")
@Setting(settingPath = "/configuration/serbian-analyzer-config.json")
public class FacilityIndex {

    @Id
    private String id;

    @Field(type = FieldType.Text, store = true, name = "name")
    private String name;

    @Field(type = FieldType.Text, store = true, analyzer = "srp", searchAnalyzer = "srp")
    private String descriptionSrp;

    @Field(type = FieldType.Text, store = true, analyzer = "eng", searchAnalyzer = "eng")
    private String descriptionEng;

    @Field(type = FieldType.Text, store = true, analyzer = "serbian_simple", name = "fileDescription_srp")
    private String fileDescriptionSrp;

    @Field(type = FieldType.Text,  store = true, analyzer = "english", name = "fileDescription_eng")
    private String fileDescriptionEng;

    @Field(type = FieldType.Text, store = true, name = "server_filename", index = false)
    private String serverFilename;

    @Field(type = FieldType.Integer, store = true, name = "reviewCount")
    private Integer reviewCount;

    @Field(type = FieldType.Double, store = true, name = "avgEquipmentGrade")
    private Double avgEquipmentGrade;

    @Field(type = FieldType.Double, store = true, name = "avgStaffGrade")
    private Double avgStaffGrade;

    @Field(type = FieldType.Double, store = true, name = "avgHygieneGrade")
    private Double avgHygieneGrade;

    @Field(type = FieldType.Double, store = true, name = "avgSpaceGrade")
    private Double avgSpaceGrade;
}
