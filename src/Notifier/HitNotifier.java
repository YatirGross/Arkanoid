// 207083395 Yatir Zeev Gross
package Notifier;
/**
 * The HitNotifier interface represents an object that can notify hit events to registered HitListeners.
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners to be notified when a hit event occurs.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a HitListener from the list of listeners.
     * The listener will no longer be notified when hit events occur.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
