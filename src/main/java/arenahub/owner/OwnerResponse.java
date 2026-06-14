package arenahub.owner;

import arenahub.account.AccountResponse;


public record OwnerResponse(
        Long id,
        String name,
        String phone,
        AccountResponse accountResponse,
        String contactEmail
//        List<ArenaResponse> arenas
) {
    public static OwnerResponse from(Owner owner){
        return new OwnerResponse(owner.getId(),
        owner.getName(),
        owner.getPhone(),
        AccountResponse.from(owner.getAccount()),
        owner.getContactEmail()
        );
    }
}
