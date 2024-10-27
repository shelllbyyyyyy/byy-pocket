import Image from "next/image";
import React, { useState } from "react";

import SubmitButton from "@/components/ui/submit-button";

const SignInWithGoogleButton = () => {
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const google = async () => {
    try {
      setIsLoading(!isLoading);

      location.href = `http://localhost:8081/oauth2/authorization/google`;
    } catch (error) {
      console.log(error);
    } finally {
      setIsLoading(!!isLoading);
    }
  };
  return (
    <SubmitButton
      className="w-full rounded-md py-5"
      isLoading={isLoading}
      variant={"neutral"}
      onClick={google}
    >
      <Image
        src={"/assets/google.svg"}
        height={20}
        width={20}
        alt="google logo"
        className="mr-2"
      />
      Continue with google
    </SubmitButton>
  );
};

export default SignInWithGoogleButton;
