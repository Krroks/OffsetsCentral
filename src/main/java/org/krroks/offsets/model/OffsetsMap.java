package org.krroks.offsets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OffsetsMap {
    public String GameTime;

    public String ObjIndex;
    public String ObjTeam;
    public String ObjNetworkID;
    public String ObjPosition;
    public String ObjHealth;
    public String ObjMaxHealth;
    public String ObjMana;
    public String ObjMaxMana;
    public String ObjDisplayNameLength;
    public String ObjDisplayName;
    public String ObjName;
    public String ObjNameLength;
    public String ObjLvl;
    public String ObjExperience;
    public String ObjCurrentGold;
    public String ObjTotalGold;

    public String ObjectManager;
    public String ObjectMapCount;
    public String ObjectMapRoot;
    public String ObjectMapNodeNetId;
    public String ObjectMapNodeObject;

    public String HeroList;
    public String MinionList;
    public String TurretList;
    public String InhibitorList;

    public String LocalPlayer;
    public String ViewProjMatrix;

    public Set<Map.Entry<String, String>> entrySet() {
        return Set.of(
                new AbstractMap.SimpleEntry<>("GameTime", GameTime),
                new AbstractMap.SimpleEntry<>("ObjIndex", ObjIndex),
                new AbstractMap.SimpleEntry<>("ObjTeam", ObjTeam),
                new AbstractMap.SimpleEntry<>("ObjNetworkID", ObjNetworkID),
                new AbstractMap.SimpleEntry<>("ObjPosition", ObjPosition),
                new AbstractMap.SimpleEntry<>("ObjHealth", ObjHealth),
                new AbstractMap.SimpleEntry<>("ObjMaxHealth", ObjMaxHealth),
                new AbstractMap.SimpleEntry<>("ObjMana", ObjMana),
                new AbstractMap.SimpleEntry<>("ObjMaxMana", ObjMaxMana),
                new AbstractMap.SimpleEntry<>("ObjDisplayNameLength", ObjDisplayNameLength),
                new AbstractMap.SimpleEntry<>("ObjDisplayName", ObjDisplayName),
                new AbstractMap.SimpleEntry<>("ObjName", ObjName),
                new AbstractMap.SimpleEntry<>("ObjNameLength", ObjNameLength),
                new AbstractMap.SimpleEntry<>("ObjLvl", ObjLvl),
                new AbstractMap.SimpleEntry<>("ObjExperience", ObjExperience),
                new AbstractMap.SimpleEntry<>("ObjCurrentGold", ObjCurrentGold),
                new AbstractMap.SimpleEntry<>("ObjTotalGold", ObjTotalGold),
                new AbstractMap.SimpleEntry<>("ObjectManager", ObjectManager),
                new AbstractMap.SimpleEntry<>("ObjectMapCount", ObjectMapCount),
                new AbstractMap.SimpleEntry<>("ObjectMapRoot", ObjectMapRoot),
                new AbstractMap.SimpleEntry<>("ObjectMapNodeNetId", ObjectMapNodeNetId),
                new AbstractMap.SimpleEntry<>("ObjectMapNodeObject", ObjectMapNodeObject),
                new AbstractMap.SimpleEntry<>("HeroList", HeroList),
                new AbstractMap.SimpleEntry<>("MinionList", MinionList),
                new AbstractMap.SimpleEntry<>("TurretList", TurretList),
                new AbstractMap.SimpleEntry<>("InhibitorList", InhibitorList),
                new AbstractMap.SimpleEntry<>("LocalPlayer", LocalPlayer),
                new AbstractMap.SimpleEntry<>("ViewProjMatrix", ViewProjMatrix)
        );
    }

    @Override
    public String toString() {
        return "OffsetsMap{" +
                "GameTime='" + GameTime + '\'' +
                ", ObjIndex='" + ObjIndex + '\'' +
                ", ObjTeam='" + ObjTeam + '\'' +
                ", ObjNetworkID='" + ObjNetworkID + '\'' +
                ", ObjPosition='" + ObjPosition + '\'' +
                ", ObjHealth='" + ObjHealth + '\'' +
                ", ObjMaxHealth='" + ObjMaxHealth + '\'' +
                ", ObjMana='" + ObjMana + '\'' +
                ", ObjMaxMana='" + ObjMaxMana + '\'' +
                ", ObjDisplayNameLength='" + ObjDisplayNameLength + '\'' +
                ", ObjDisplayName='" + ObjDisplayName + '\'' +
                ", ObjName='" + ObjName + '\'' +
                ", ObjNameLength='" + ObjNameLength + '\'' +
                ", ObjLvl='" + ObjLvl + '\'' +
                ", ObjExperience='" + ObjExperience + '\'' +
                ", ObjCurrentGold='" + ObjCurrentGold + '\'' +
                ", ObjTotalGold='" + ObjTotalGold + '\'' +
                ", ObjectManager='" + ObjectManager + '\'' +
                ", ObjectMapCount='" + ObjectMapCount + '\'' +
                ", ObjectMapRoot='" + ObjectMapRoot + '\'' +
                ", ObjectMapNodeNetId='" + ObjectMapNodeNetId + '\'' +
                ", ObjectMapNodeObject='" + ObjectMapNodeObject + '\'' +
                ", HeroList='" + HeroList + '\'' +
                ", MinionList='" + MinionList + '\'' +
                ", TurretList='" + TurretList + '\'' +
                ", InhibitorList='" + InhibitorList + '\'' +
                ", LocalPlayer='" + LocalPlayer + '\'' +
                ", ViewProjMatrix='" + ViewProjMatrix + '\'' +
                '}';
    }
}
