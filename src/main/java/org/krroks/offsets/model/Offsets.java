package org.krroks.offsets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.krroks.offsets.model.serializer.HexIntSerializer;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Offsets {
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("gameTime")
    private int GameTime = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objIndex")
    private int ObjIndex = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objTeam")
    private int ObjTeam = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objNetworkID")
    private int ObjNetworkID = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objPosition")
    private int ObjPosition = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objHealth")
    private int ObjHealth = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objMaxHealth")
    private int ObjMaxHealth = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objMana")
    private int ObjMana = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objMaxMana")
    private int ObjMaxMana = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objDisplayNameLength")
    private int ObjDisplayNameLength = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objDisplayName")
    private int ObjDisplayName = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objName")
    private int ObjName = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objNameLength")
    private int ObjNameLength = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objLvl")
    private int ObjLvl = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objExperience")
    private int ObjExperience = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objCurrentGold")
    private int ObjCurrentGold = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objTotalGold")
    private int ObjTotalGold = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objectManager")
    private int ObjectManager = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objectMapCount")
    private int ObjectMapCount = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objectMapRoot")
    private int ObjectMapRoot = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objectMapNodeNetId")
    private int ObjectMapNodeNetId = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("objectMapNodeObject")
    private int ObjectMapNodeObject = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("heroList")
    private int HeroList = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("minionList")
    private int MinionList = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("turretList")
    private int TurretList = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("inhibitorList")
    private int InhibitorList = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("localPlayer")
    private int LocalPlayer = 0x0;
    @JsonSerialize(using = HexIntSerializer.class)
    @JsonProperty("viewProjMatrix")
    private int ViewProjMatrix = 0x0;

    private String version;
    
    public void add(String name, int value) {
        switch (name){
            case "GameTime":
                GameTime = value;
                break;
            case "ObjIndex":
                ObjIndex = value;
                break;
            case "ObjTeam":
                ObjTeam = value;
                break;
            case "ObjNetworkID":
                ObjNetworkID = value;
                break;
            case "ObjPosition":
                ObjPosition = value;
                break;
            case "ObjHealth":
                ObjHealth = value;
                break;
            case "ObjMaxHealth":
                ObjMaxHealth = value;
                break;
            case "ObjMana":
                ObjMana = value;
                break;
            case "ObjMaxMana":
                ObjMaxMana = value;
                break;
            case "ObjDisplayNameLength":
                ObjDisplayNameLength = value;
                break;
            case "ObjDisplayName":
                ObjDisplayName = value;
                break;
            case "ObjName":
                ObjName = value;
                break;
            case "ObjNameLength":
                ObjNameLength = value;
                break;
            case "ObjLvl":
                ObjLvl = value;
                break;
            case "ObjExperience":
                ObjExperience = value;
                break;
            case "ObjCurrentGold":
                ObjCurrentGold = value;
                break;
            case "ObjTotalGold":
                ObjTotalGold = value;
                break;
            case "ObjectManager":
                ObjectManager = value;
                break;
            case "ObjectMapCount":
                ObjectMapCount = value;
                break;
            case "ObjectMapRoot":
                ObjectMapRoot = value;
                break;
            case "ObjectMapNodeNetId":
                ObjectMapNodeNetId = value;
                break;
            case "ObjectMapNodeObject":
                ObjectMapNodeObject = value;
                break;
            case "HeroList":
                HeroList = value;
                break;
            case "MinionList":
                MinionList = value;
                break;
            case "TurretList":
                TurretList = value;
                break;
            case "InhibitorList":
                InhibitorList = value;
                break;
            case "LocalPlayer":
                LocalPlayer = value;
                break;
            case "ViewProjMatrix":
                ViewProjMatrix = value;
                break;

        }
    }

    public void putAll(Offsets offsets1) {
        GameTime = (offsets1.GameTime != 0) ? offsets1.GameTime : this.GameTime;
        ObjIndex = (offsets1.ObjIndex != 0) ? offsets1.ObjIndex : this.ObjIndex;
        ObjTeam = (offsets1.ObjTeam != 0) ? offsets1.ObjTeam : this.ObjTeam;
        ObjNetworkID = (offsets1.ObjNetworkID != 0) ? offsets1.ObjNetworkID : this.ObjNetworkID;
        ObjPosition = (offsets1.ObjPosition != 0) ? offsets1.ObjPosition : this.ObjPosition;
        ObjHealth = (offsets1.ObjHealth != 0) ? offsets1.ObjHealth : this.ObjHealth;
        ObjMaxHealth = (offsets1.ObjMaxHealth != 0) ? offsets1.ObjMaxHealth : this.ObjMaxHealth;
        ObjMana = (offsets1.ObjMana != 0) ? offsets1.ObjMana : this.ObjMana;
        ObjMaxMana = (offsets1.ObjMaxMana != 0) ? offsets1.ObjMaxMana : this.ObjMaxMana;
        ObjDisplayNameLength = (offsets1.ObjDisplayNameLength != 0) ? offsets1.ObjDisplayNameLength : this.ObjDisplayNameLength;
        ObjDisplayName = (offsets1.ObjDisplayName != 0) ? offsets1.ObjDisplayName : this.ObjDisplayName;
        ObjName = (offsets1.ObjName != 0) ? offsets1.ObjName : this.ObjName;
        ObjNameLength = (offsets1.ObjNameLength != 0) ? offsets1.ObjNameLength : this.ObjNameLength;
        ObjLvl = (offsets1.ObjLvl != 0) ? offsets1.ObjLvl : this.ObjLvl;
        ObjExperience = (offsets1.ObjExperience != 0) ? offsets1.ObjExperience : this.ObjExperience;
        ObjCurrentGold = (offsets1.ObjCurrentGold != 0) ? offsets1.ObjCurrentGold : this.ObjCurrentGold;
        ObjTotalGold = (offsets1.ObjTotalGold != 0) ? offsets1.ObjTotalGold : this.ObjTotalGold;
        ObjectManager = (offsets1.ObjectManager != 0) ? offsets1.ObjectManager : this.ObjectManager;
        ObjectMapCount = (offsets1.ObjectMapCount != 0) ? offsets1.ObjectMapCount : this.ObjectMapCount;
        ObjectMapRoot = (offsets1.ObjectMapRoot != 0) ? offsets1.ObjectMapRoot : this.ObjectMapRoot;
        ObjectMapNodeNetId = (offsets1.ObjectMapNodeNetId != 0) ? offsets1.ObjectMapNodeNetId : this.ObjectMapNodeNetId;
        ObjectMapNodeObject = (offsets1.ObjectMapNodeObject != 0) ? offsets1.ObjectMapNodeObject : this.ObjectMapNodeObject;
        HeroList = (offsets1.HeroList != 0) ? offsets1.HeroList : this.HeroList;
        MinionList = (offsets1.MinionList != 0) ? offsets1.MinionList : this.MinionList;
        TurretList = (offsets1.TurretList != 0) ? offsets1.TurretList : this.TurretList;
        InhibitorList = (offsets1.InhibitorList != 0) ? offsets1.InhibitorList : this.InhibitorList;
        LocalPlayer = (offsets1.LocalPlayer != 0) ? offsets1.LocalPlayer : this.LocalPlayer;
        ViewProjMatrix = (offsets1.ViewProjMatrix != 0) ? offsets1.ViewProjMatrix : this.ViewProjMatrix;
        version = offsets1.version;
    }
}
