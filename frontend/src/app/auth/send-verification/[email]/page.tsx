import React, { use } from "react";

import SendVerificationPage from "@/components/pages/send-verification-page";

const SendVerification = ({
  params,
}: {
  params: Promise<{ email: string }>;
}) => {
  const { email } = use(params);

  return <SendVerificationPage email={email} />;
};

export default SendVerification;
