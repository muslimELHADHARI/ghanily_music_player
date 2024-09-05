/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc_player.models;

/**
 *
 * @author SGI
 */
public class Languages {
    public static boolean isContainsArabic(String s){
   for(int i=0;i<s.length();i++){
   if(s.charAt(i)>=1569 && s.charAt(i)<=1610){return true;}
   }
   return false;
    }
    
}
