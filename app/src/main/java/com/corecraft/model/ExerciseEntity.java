package com.corecraft.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.corecraft.R;
import com.corecraft.TargetMuscles;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "exercise")
public class ExerciseEntity {

    @Override
    public String toString() {
        return "ExerciseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", target=" + target +
                ", withEquipment=" + withEquipment +
                ", image=" + image +
                ", video=" + video +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    public static final List<ExerciseEntity> EXERCISES = new ArrayList<>();
    static{
        EXERCISES.add(new ExerciseEntity("Bicep Curls","Popular exercise that targets the biceps brachii muscle, which is responsible for flexing the elbow and forearm", TargetMuscles.ARM,true, R.drawable.bicep_curl,R.drawable.bicep_curl_vid,"1.) Start by standing with your feet shoulders width apart.\n\u20282.) Pick up the barbells using a palm inward grip.\n\u20283.) Curl each barbell alternating each time.\n\u20284.) Repeat for the desired amount of reps."));
        EXERCISES.add(new ExerciseEntity("Machine Assisted Dips","Classical chest exercise, requires strong shoulder",TargetMuscles.CHEST | TargetMuscles.ARM,true,R.drawable.dip,R.drawable.dip_vid,""));
        EXERCISES.add(new ExerciseEntity("Squats","Classical leg exercise",TargetMuscles.LEG,true,R.drawable.squat,R.drawable.squat_vid,""));
    }
    public static int ID = 0;
    @PrimaryKey
    int id;
    String name;
    String description;
    int target;
    boolean withEquipment;
    int image;
    int video;
    String instructions;

    public ExerciseEntity(String name, String description, int target, boolean withEquipment, int image, int video, String instructions) {
        this.id = ID;
        ID++;
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
