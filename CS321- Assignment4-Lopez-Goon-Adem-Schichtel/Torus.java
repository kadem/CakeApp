package com.tutsplus.opengltutorial;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Created by Hathibelagal on 14/3/17.
 *  The Cylinder models only. Used in the 3D modeling class.
 */

public class Torus {

    private FloatBuffer verticesBuffer;
    private ShortBuffer facesBuffer;
    private List<String> verticesList;
    private List<String> facesList;

    private int program;

    public Torus(Context context) {
        verticesList = new ArrayList<>();
        facesList = new ArrayList<>();

        //scan in the cake (cylinder) text file
        try {
            Scanner scanner = new Scanner(context.getAssets().open("cake1.obj"));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.startsWith("v ")) {
                    verticesList.add(line);
                } else if(line.startsWith("f ")) {
                    facesList.add(line);
                }
            }
            scanner.close();

            // Create buffer for vertices
            ByteBuffer buffer1 = ByteBuffer.allocateDirect(verticesList.size() * 3 * 4);
            buffer1.order(ByteOrder.nativeOrder());
            verticesBuffer = buffer1.asFloatBuffer();

            // Create buffer for faces
            ByteBuffer buffer2 = ByteBuffer.allocateDirect(facesList.size() * 3 * 2);
            buffer2.order(ByteOrder.nativeOrder());
            facesBuffer = buffer2.asShortBuffer();

            for(String vertex: verticesList) {
                String coords[] = vertex.split(" ");
                float x = Float.parseFloat(coords[1]);
                float y = Float.parseFloat(coords[2]);
                float z = Float.parseFloat(coords[3]);
                verticesBuffer.put(x);
                verticesBuffer.put(y);
                verticesBuffer.put(z);
            }
            verticesBuffer.position(0);

            for(String face: facesList) {
                String vertexIndices[] = face.split(" ");
                short vertex1 = Short.parseShort(vertexIndices[1]);
                short vertex2 = Short.parseShort(vertexIndices[2]);
                short vertex3 = Short.parseShort(vertexIndices[3]);
                facesBuffer.put((short)(vertex1 - 1));
                facesBuffer.put((short)(vertex2 - 1));
                facesBuffer.put((short)(vertex3 - 1));
            }
            facesBuffer.position(0);

            InputStream vertexShaderStream = context.getResources().openRawResource(R.raw.vertex_shader);
            String vertexShaderCode = IOUtils.toString(vertexShaderStream, Charset.defaultCharset());
            vertexShaderStream.close();

            InputStream fragmentShaderStream = context.getResources().openRawResource(R.raw.fragment_shader);
            String fragmentShaderCode = IOUtils.toString(fragmentShaderStream, Charset.defaultCharset());
            fragmentShaderStream.close();

            int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
            GLES20.glShaderSource(vertexShader, vertexShaderCode);

            int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            GLES20.glShaderSource(fragmentShader, fragmentShaderCode);

            GLES20.glCompileShader(vertexShader);
            GLES20.glCompileShader(fragmentShader);

            program = GLES20.glCreateProgram();
            GLES20.glAttachShader(program, vertexShader);
            GLES20.glAttachShader(program, fragmentShader);
            GLES20.glLinkProgram(program);

            GLES20.glUseProgram(program);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //draw the cylinder to the GLSurfaceView in the 3D modeling class
    public void draw(int i, int inch, int color) {
        int position = GLES20.glGetAttribLocation(program, "position");
        GLES20.glEnableVertexAttribArray(position);
        GLES20.glVertexAttribPointer(position, 3, GLES20.GL_FLOAT, false, 3 * 4, verticesBuffer);

        float[] projectionMatrix = new float[16];
        float[] viewMatrix = new float[16];
        float[] productMatrix = new float[16];

        Matrix.frustumM(projectionMatrix, 0, -1, 1, -1, 1, 2, 9);

        Matrix.setLookAtM(viewMatrix, 0, 0, 3, -4, 0, 0, 0, 0, 1, 0f);

        Matrix.multiplyMM(productMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

//        if(color != -1) {
//            final String fragmentShaderCode =
//                    "precision mediump float;" +
//                            "void main() {" +
//                            "  gl_FragColor = vec4(0.315, 1.000, 0.615, 1.000);" +
//                            "}";
//
//            int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
//            GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
//
//            GLES20.glCompileShader(fragmentShader);
//
//            program = GLES20.glCreateProgram();
//            GLES20.glAttachShader(program, fragmentShader);
//            GLES20.glLinkProgram(program);
//
//            GLES20.glUseProgram(program);
//        }

        //if(i != -1) {
            switch (inch) {
                case 16:
                    Matrix.scaleM(productMatrix, 0, 2.0f, 1.0f, 2.0f);
                    break;
                case 14:
                    Matrix.scaleM(productMatrix, 0, 1.8f, 1.0f, 1.8f);
                    break;
                case 12:
                    Matrix.scaleM(productMatrix, 0, 1.4f, 1.0f, 1.4f);
                    break;
                case 10:
                    Matrix.scaleM(productMatrix, 0, 1.0f, 1.0f, 1.0f);
                    break;
                case 8:
                    Matrix.scaleM(productMatrix, 0, 0.8f, 1.0f, 0.8f);
                    break;
                case 6:
                    Matrix.scaleM(productMatrix, 0, 0.6f, 1.0f, 0.6f);
                    break;
                case 4:
                    Matrix.scaleM(productMatrix, 0, 0.4f, 1.0f, 0.4f);
                    break;
                default:
                    break;
            }
        //}

        if(i == 1){
            Matrix.translateM(productMatrix, 0, 0.0f, -1.0f, 0.0f);
        }else if(i == 3){
            Matrix.translateM(productMatrix, 0, 0.0f, 1.0f, 0.0f);
        }else if(i == 4){
            Matrix.translateM(productMatrix, 0, 0.0f, 1.5f, 0.0f);
        }

        int matrix = GLES20.glGetUniformLocation(program, "matrix");
        GLES20.glUniformMatrix4fv(matrix, 1, false, productMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, facesList.size() * 3, GLES20.GL_UNSIGNED_SHORT, facesBuffer);
        GLES20.glDisableVertexAttribArray(position);

    }
}
