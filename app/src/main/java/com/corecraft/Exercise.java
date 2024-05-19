package com.corecraft;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    public static final List<Exercise> EXERCISES = new ArrayList<>();
    static{
        EXERCISES.add(new Exercise(0,"Bicep Curls","Popular exercise that targets the biceps brachii muscle, which is responsible for flexing the elbow and forearm",TargetMuscles.ARM,true,R.drawable.bicep_curl,R.drawable.bicep_curl_vid,"1.) Start by standing with your feet shoulders width apart.\n\u20282.) Pick up the barbells using a palm inward grip.\n\u20283.) Curl each barbell alternating each time.\n\u20284.) Repeat for the desired amount of reps."));
        EXERCISES.add(new Exercise(1,"Machine Assisted Dips","Classical chest exercise, requires strong shoulder",TargetMuscles.CHEST | TargetMuscles.ARM,true,R.drawable.dip,R.drawable.dip_vid,""));
        EXERCISES.add(new Exercise(2,"Squats","Classical leg exercise",TargetMuscles.LEG,true,R.drawable.squat,R.drawable.squat_vid,""));
    }
    int id;
    String name;
    String description;
    int target;
    boolean withEquipment;
    int image;
    int video;
    String instructions;

    public Exercise(int id, String name, String description, int target, boolean withEquipment, int image, int video, String instructions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.target = target;
        this.withEquipment = withEquipment;
        this.image = image;
        this.video = video;
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

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
