package tcpWork;

import tcpWork.MetroCard;

/**
 *
 * @author maxkrivich
 */
public class AddMetroCardOperation extends CardOperation
{

    private MetroCard card;

    public AddMetroCardOperation()
    {
        card = new MetroCard();
    }

    public MetroCard getCard()
    {
        return card;
    }

}
