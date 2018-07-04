package com.cxh.stream;

import java.io.*;

public class StreamUitl {
    /**
     * 可以从输入流读取文件，保存到指定路径下<br>
     * 注意：该方法会关流
     * @param in
     */
    public static void saveFileByStream(InputStream in,String path){
        BufferedOutputStream bos=null;
        try{
            bos=new BufferedOutputStream(new FileOutputStream(path));
            byte b[]=new byte[1024];
            int len=0;
            while((len=in.read(b))>-1){
                bos.write(b, 0, len);
            }
            bos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(bos!=null){
                    bos.close();
                }
                in.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 将字节流转换成字符串
     * @param in 需要转换的字节流
     * @param characterSet 字符编码
     * @return 转换完毕的字符串
     * @throws IOException
     */
    public static String inputStreamConversionString(InputStream in,String characterSet) throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(in,characterSet));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while((line = read.readLine())  !=  null){
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * 将输入流转成字节数组
     * @param input 输入流
     * @return 字节数组
     * Note: This Method has close Stream!
     */
    public static byte[] inputStreamToByte(InputStream input) throws IOException {
        try {
            if (input == null){
                throw new IllegalArgumentException("input Stream must be not Null");
            }
            BufferedInputStream in = new BufferedInputStream(input);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[]  temp = new byte[1024];
            int len ;
            while((len = in.read(temp)) !=-1){
                outputStream.write(temp,0,len);
            }
            return outputStream.toByteArray();
        } finally {
            input.close();
        }
    }


}
