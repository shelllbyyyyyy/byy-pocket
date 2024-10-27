"use client";

import { use } from "react";
import ActivateAccountPage from "@/components/pages/activate-account-page";

export default function VerifyUser({
  params,
}: {
  params: Promise<{ email: string; token: string }>;
}) {
  const { token } = use(params);

  return <ActivateAccountPage token={token} />;
}
