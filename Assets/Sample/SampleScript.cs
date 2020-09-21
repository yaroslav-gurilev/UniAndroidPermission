using UnityEngine;
using UnityEngine.UI;

public class SampleScript : MonoBehaviour
{

    [SerializeField] Text text;

    public void RequestPermission()
    {
        if (UniAndroidPermission.IsPermitted (AndroidPermission.WRITE_EXTERNAL_STORAGE))
        {
            text.text = "WRITE_EXTERNAL_STORAGE is already permitted!!";
            return;
        }

        UniAndroidPermission.RequestPremission (AndroidPermission.WRITE_EXTERNAL_STORAGE, () =>
        {
            text.text = "WRITE_EXTERNAL_STORAGE is permitted NOW";
        }, (always) =>
        {
            if (always)
                text.text = "WRITE_EXTERNAL_STORAGE is NOT permitted forever";
            else
                text.text = "WRITE_EXTERNAL_STORAGE is NOT permitted";
        });
    }
}
