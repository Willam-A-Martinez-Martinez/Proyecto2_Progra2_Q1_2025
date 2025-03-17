/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Musica {
    private Clip clip;
    float volumen1=0,volumen2=0;
    FloatControl fc;
    boolean mute;
    
    public void setMusic(File mcFile){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(mcFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            
        }catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void volumeUp(){
        if(mute==false){
            volumen1+=1;
            if(volumen1>6.0f){
                volumen1=6.0f;
            }
            fc.setValue(volumen1);
        }
    }
    public void volumeDown(){
        if(mute==false){
            volumen1-=1;
            if(volumen1==-40.0f){
                volumen1=-80.0f;
            }
            fc.setValue(volumen1);
        }
    }
    public void volumeMute(){
        if(mute==false){
            volumen2=volumen1;
            volumen1=-80.0f;
            fc.setValue(volumen1);
            mute=true;
        }
        else if(mute==true){
            volumen1=volumen2;
            fc.setValue(volumen1);
            mute=false;
        }
    }
    
}
