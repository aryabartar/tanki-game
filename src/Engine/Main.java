package Engine;

import Engine.GameFrame;
import Engine.GameLoop;
import Engine.Start;
import Engine.ThreadPool;
import Network.GameClient;
import Network.GameServer;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;


/**
 * Program start.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {

    public static void main(String[] args)  {
        ThreadPool.init();
        new Start();

    }
}
