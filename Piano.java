/**
 * * Name: (Risto Zimbakov)
 * Teacher: Mr. Hardman
 * Assignment #5, Piano array 
 * Date Last Modified: 5/5/2017
 */
import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

public class Piano extends World
{
    private String[] whiteKeys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]"};
    private String[] whiteNotes = {"3c", "3d", "3e", "3f", "3g", "3a","3b", "4c", "4d", "4e", "4f", "4g"};
    private String[] blackKeys = {"2", "3","", "5", "6", "7","", "9", "0","", "="}; 
    private String[] blackNotes= {"3c#", "3d#","", "3f#", "3g#", "3a#","", "4c#", "4d#","", "4f#"};

    private Key[] whiteKeyObjects = new Key[whiteKeys.length];
    private Key[] blackKeyObjects = new Key[blackKeys.length];
    private Key[] allKeyObjects = new Key[whiteKeys.length + blackKeys.length];

    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();
    }

    /**
     * makeKeys adds key objects to the world
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void makeKeys()
    {
        Key currentKey;
        for( int i = 0; i < whiteKeys.length; i++ )
        {
            currentKey = new Key( whiteKeys[i],whiteNotes[i], "white-key", "white-key-down" );
            addObject( currentKey,(i*67)+30, 250);
            whiteKeyObjects[i] = currentKey;
        }

        for( int i = 0; i < blackKeys.length; i++ )
        {
            if(blackKeys[i] != "")
            {
                currentKey = new Key( blackKeys[i], blackNotes[i], "black-key", "black-key-down" );
                addObject( currentKey,(i*67)+65, 195);
                blackKeyObjects[i] = currentKey;
            }
            else
            {
                blackKeyObjects[i] = null;
            }
            makeAllKeys();
        }

    }

    /**
     * makeAllKeys fills every even index with whiteKeyObjects and every
     * odd index with blackKeyObjects.
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void makeAllKeys()
    {
        for( int i = 0; i < allKeyObjects.length; i++ )
        {
            if( i % 2 == 0 )
            {
                allKeyObjects[i] = whiteKeyObjects[i/2];
            }
            else
            {
                allKeyObjects[i] = blackKeyObjects[i/2];
            }
        }
        allKeyObjects[allKeyObjects.length - 1] = whiteKeyObjects[whiteKeyObjects.length - 1];
    }

    public void act()
    {
        int numAllDown = 0;
        int numNulls = 0;

        int[] keyDownLocations = new int[20];

        for( int i = 0; i < allKeyObjects.length; i++ )
        {
            if( allKeyObjects[i] == null )
            {
                numNulls ++;
            }
            else
            {
                if( allKeyObjects[i].checkDown() == true )
                {
                    keyDownLocations[numAllDown] = i - numNulls;
                    numAllDown ++;
                }
            }
        }

        if( numAllDown == 2)
        {
            checkForSeconds(keyDownLocations);           
        }
        else if( numAllDown == 3)
        {
            checkForTriads(keyDownLocations);
            checkForInvertedTriads(keyDownLocations);
        }
        else if( numAllDown == 4)
        {
            checkForSevenths(keyDownLocations);
        }
        else
        {
            showText("", getWidth()/2, 50);
        }
    }

    /**
     * checkForSeconds sees whether one or more downKeys is pressed down and
     * if so displays text showing the user that they play a second
     * 
     * @param an integer array downKeys that checks if the correct keys to 
     * make a second are pressed 
     * @return Nothing is returned
     */
    private void checkForSeconds(int[] downKeys)
    {
        if( downKeys[0] + 1 == downKeys[1] || downKeys[0] + 2 == downKeys[1] )
        {
            showText("You have made a second", getWidth()/2, 50);
        }
    }

    /**
     * checkForTriads sees whether a combination of keys is pressed down and
     * if so displays text showing the user that they played a traid
     * 
     * @param an integer array downKeys that checks if the correct keys to 
     * make a triad are pressed 
     * @return Nothing is returned
     */
    private void checkForTriads(int[] downKeys)
    {
        if( downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] ||
        downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] ||
        downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] )
        {
            showText("You have made a triad", getWidth()/2, 50);
        }
    }

    /**
     * checkForSevenths sees whether a even bigger combination of keys is pressed down and
     * if so displays text showing the user that they played a seventh
     * 
     * @param an integer array downKeys that checks if the correct keys to 
     * make a seventh are pressed 
     * @return Nothing is returned
     */
    private void checkForSevenths(int[] downKeys)
    {
        if( downKeys[0] + 3 == downKeys[1] && downKeys[1] + 4 == downKeys[2] && downKeys[2] + 3 == downKeys[3] ||
        downKeys[0] + 4 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 4 == downKeys[3] ||
        downKeys[0] + 3 == downKeys[1] && downKeys[1] + 3 == downKeys[2] && downKeys[2] + 3 == downKeys[3] )
        {
            showText("You have made a seventh", getWidth()/2, 50);
        }
    }
    
    /**
     * checkForInvertedTriads sees whether a combination of keys is pressed down and
     * if so displays text showing the user that they played an inverted traid
     * 
     * @param an integer array downKeys that checks if the correct keys to 
     * make an inverted triad are pressed 
     * @return Nothing is returned
     */
    private void checkForInvertedTriads(int[] downKeys)
    {
        if( downKeys[0] + 3 == downKeys[1] && downKeys[1] + 5 == downKeys[2] ||
        downKeys[0] + 5 == downKeys[1] && downKeys[1] + 4 == downKeys[2] ||
        downKeys[0] + 4 == downKeys[1] && downKeys[1] + 5 == downKeys[2] )
        {
            showText("You have made a inverted triad", getWidth()/2, 50);
        }
    }
}