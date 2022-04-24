package lz;
import java.util.*;
public class LZ {
String search, data;
int targetLen, pos, Start, searchWindow = 15;
ArrayList <Tag> tags=new ArrayList<Tag>();
Tag t;
class Tag{
    int offset;
    int length;
    String Next;
    Tag(int pos,int len,String next){
      offset = pos;
      length = len;
      Next = next;
    }
    public String toString(){
      return offset + "," + length + "," + Next ;
    }
}
void compress(String data){
    this.data=data;
    int count=0;
    while(count < data.length()){
        Start = 0;
        System.out.print(data.substring(Start,count));
        targetLen = 1;
        String target = data.substring(count,count + targetLen);
        if(count == 0)
            search = "";
        else
            search = data.substring(Start,count);
        if(search.indexOf(target) != -1){
            targetLen++;
            while(search.indexOf(target) != -1){
                if(((count + targetLen)>= data.length()))
                    break;
                target = data.substring(count,count+targetLen);
                pos = search.indexOf(data.substring(count,count+targetLen));
                if(pos != -1) { 
                    if(((count + targetLen)< data.length())){
                        targetLen++;
                    }}
                else{break;}
            }
            targetLen--;
            pos = search.indexOf(data.substring(count,count+targetLen));
            count =count+targetLen;
            int offset;
            offset=count-pos-targetLen;
            String nextChar;
            if(count==data.length())
                nextChar="";
           else
                nextChar = data.substring(count,count+1);
            t = new Tag(offset,targetLen,nextChar);
            tags.add(t);
        }else{
            String nextChar = data.substring(count,count+1);
            t = new Tag(0,0,nextChar);
            tags.add(t);
        }
        count++;
        System.out.println( " <"+t+ ">");
    } 
    System.out.println("'"+data+"'");
}
void extract(){
    decompress(tags);
}
void decompress (ArrayList<Tag> tag){
    System.out.println("\ndecompressed version");
    String text="";
    for(int i=0;i<tags.size();i++){
        Tag temp=tags.get(i);
        for(int j=text.length()-temp.offset,k=0;k<temp.length;j++,k++)
            text=text+text.charAt(j);
        text=text+temp.Next;
        System.out.println("<"+temp+"> "+text);

    }
    System.out.println("'"+text+"'");
}    
public static void main(String[] args) {
    String line="aaabbbbccd";
    LZ lz77 = new LZ();
    lz77.compress(line);
    lz77.extract();
    }
}
