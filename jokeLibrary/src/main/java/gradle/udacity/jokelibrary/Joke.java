package gradle.udacity.jokelibrary;



import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Joke {

    final List<String> jokes = Arrays.asList("A blonde walks in to a store she asks the salesman \"How much for the T.V?\"\n The sales person says \"Madam we don't sell to blondes.\"\n The next day she colors her hair black then she goes back into the store and she asks again\n \"How much for that T.V?\"\n The sales man says \"Madam we don't sell to blondes!\"\n Upset her plan didn't work she \"How do you know I'm a blonde?\"\n The salesman replied \" It's not a T.V. it's a microwave!\" ",
            "An Irishman fell a hundred feet from a building site and asked if he was hurt by the fall.\n \"Indeed not,\" he replied, \n\"It wasn't the fall that hurt me at all, it was the landing.\"",
            "Q how do you confuse a blond?\n A. draw a circle and tell her to sit in the corner",
            "There are 3 women who escape form a jail. a blonde, a redhead, and a brunette. \nThey see 3 potato sacks on the edge of the road, and each jumps in one to hide from the police.\n 2 officers see the sacks \"lets check these sacks\" the first one says. he kicks the redhead's sack,\n \"Meow!\" she says \"nothing but a cat in this one then he kicks the brunette's, \"Woof!\" she says, \n\"Nothing but a dog in this one! he kick's the blonde's sack \"Po-ta-to!\" ",
            "Q. imagine you're in a haunted house with monsters and ghosts surrounding you....how do you survive?\n A. stop imaging!",
            "A Girl was in the hospital, her parents sat waiting in the waiting room...finally the doctor comes out. Both parents jump up, and the whole room watches. \"Is it serious?\" the mom asked. \"She needs a brain transplant\" the doctor replies. Both parents stand silent for a moment...then the father asks, \"How much is it gonna cost?\"\n" + "The brain? Girl's are 450 dollars, and boy's are 5,800dollars\"\nAll the men in the room seem to chuckle to themselves, then finally the fatherbrings himself to ask, \"Why are the boys more expensive than the girl's?\" THe doctor looks at him and replies, \"We have to mark the girl's down because they're used.\"",
            "What is the difference between a Blonde and a shopping trolley?\n A shopping trolley has a mind of its own!",
            "A couple of New Jersey hunters are out in the woods when one of them falls to the ground. He doesn't seem to be breathing, his eyes are rolled back in his head.\nThe other guy whips out his cell phone and calls the emergency services. He gasps to the operator: \"My friend is dead! What can I do?\"\nThe operator, in a calm soothing voice says: \"Just take it easy. I can help. First, let's make sure he's dead.\"\nThere is a silence, then a shot is heard. The guy's voice comes back on the line. He says: \"OK, now what?\" ",
            "How do you annoy an archaeologist?\ngive him a tampon and ask what period it came from,\nA blonde, a brunette, and a red head were in an elevator when a handsome man stepped in. After a couple of floors he leaves the elevator .\nAfter he left the red head said \"Man was he hot!\" the brunette said \"Yeah but he could use some head and shoulders\"\nThe blonde thought for a while and said \"How do you give a man shoulders?\"",
            "why did the turkey cross the road?\nTo prove he wasn't chicken! ");

    public Joke() {
    }

    public String getJoke(){
        Random random = new Random();
        int jokeIndex = random.nextInt(jokes.size());
        return jokes.get(jokeIndex);
    }

}