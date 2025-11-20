package com.carolin.invasiveplants.Controller;

import com.carolin.invasiveplants.Entity.User;
import com.carolin.invasiveplants.ResponseDTO.ListRewardResponseDTO;
import com.carolin.invasiveplants.ResponseDTO.UserPointsResponseDto;
import com.carolin.invasiveplants.Service.RewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    // ################################ LIST REWARDS #############################################

    @GetMapping("/list-rewards")
    public ResponseEntity<List<ListRewardResponseDTO>> listRewards(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(rewardService.listRewads(user));

    }

    // ################################ PICK A REWARD #############################################

    @PutMapping("/redeem/{rewardId}")
    public ResponseEntity<String>redeemReward(
            @PathVariable Long rewardId,
            @AuthenticationPrincipal User user){

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        rewardService.pickReward(user,rewardId);
        return ResponseEntity.ok("Reward picked successfully");
    }

    // ################################ USERS POINT #############################################

    @GetMapping("/points")
    public ResponseEntity<UserPointsResponseDto> getUserPoints(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(rewardService.getUserPoints(user));
    }

}
