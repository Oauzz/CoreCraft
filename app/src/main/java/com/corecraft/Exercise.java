package com.corecraft;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    public static final List<Exercise> EXERCISES = new ArrayList<>();
    static{
        EXERCISES.add(new Exercise(1,"Bicep Curls","Classical bicep exercise",TargetMuscles.ARM,true,R.drawable.bicep_curl,""));
        EXERCISES.add(new Exercise(2,"Machine Assisted Dips","Classical chest exercise, requires strong shoulder",TargetMuscles.CHEST | TargetMuscles.ARM,true,R.drawable.dip,""));
        EXERCISES.add(new Exercise(3,"Squats","Classical leg exercise",TargetMuscles.LEG,true,R.drawable.squat,""));
    }
    int id;
    String name;
    String description;
    int target;
    boolean withEquipment;
    int image;
    //int video;
    String instructions;

    public Exercise(int id, String name, String description, int target, boolean withEquipment, int image, String instructions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.target = target;
        this.withEquipment = withEquipment;
        this.image = image;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isWithEquipment() {
        return withEquipment;
    }

    public void setWithEquipment(boolean withEquipment) {
        this.withEquipment = withEquipment;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
