package net.revenant;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import net.revenant.opengl.OpenGL;

public class Revenant {
    
    private static PrimaryWindow mainWindow;
    public static long windowHandle;
    
    public static void preInitGL() {
        
    }
    
    public static void initGL() {
        // Bind context
        GLFW.glfwMakeContextCurrent(windowHandle);     // Make the OpenGL context current
        GLFW.glfwShowWindow(windowHandle);             // Make the window visible
        GLContext.createFromCurrent();                 // Bind lwjgl with GLFW
        
        OpenGL.standardSetup();
        OpenGL.shaderSetup();
        
        GLFW.glfwSwapInterval(0);
    }
    
    public static void init() {
        System.out.println("LWJGL Version: ["+Sys.getVersion()+"]");
        System.out.println("OpenGL Version: ["+GL11.glGetString(GL11.GL_VERSION)+"]");
    }
    
    public static void gameStart() {
        
        while (GLFW.glfwWindowShouldClose(windowHandle) == GL11.GL_FALSE) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            
            input();
            update();
            render();
            
            GLFW.glfwSwapBuffers(windowHandle);
        }
        // Release window and window call backs
        GLFW.glfwDestroyWindow(windowHandle);
        mainWindow.getKeyCallback().release();
        exit();
    }
    
    public static void input() {
        glfwPollEvents();
    }
    
    public static void update() {
        
    }
    
    public static void render() {
        
    }
    
    public static void exit() {
        // Terminate GLFW and release the GLFWerrorfun
        GLFW.glfwTerminate();
        mainWindow.getErrorCallback().release();
        System.exit(1);
    }
    
    public static void main(String[] args) {
        mainWindow = new PrimaryWindow(480, 270, "Revenant", 0, 0);
        mainWindow.setup();
        windowHandle = mainWindow.getWindowHandler();
        
        
        Revenant.preInitGL();
        Revenant.initGL();
        Revenant.init();
        Revenant.gameStart();
    }
}
