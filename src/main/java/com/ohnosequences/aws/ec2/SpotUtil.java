/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ohnosequences.aws.ec2;

import com.amazonaws.services.ec2.AmazonEC2Client;
import java.util.List;

/**
 *
 * @author <a href="mailto:ppareja@ohnosequences.com">Pablo Pareja-Tobes</a>
 */
public class SpotUtil {

    /**
     *
     * @param spotIds spotIds List with the ids of the spot requests that must be
     * cancelled
     * @param ec2 Amazon EC2 client
     */
    public static void cancelSpotRequests(List<String> spotIds,
                                            AmazonEC2Client ec2){

        //ec2.cancelSpotInstanceRequests(new CancelSpotInstanceRequestsRequest())

    }

}
